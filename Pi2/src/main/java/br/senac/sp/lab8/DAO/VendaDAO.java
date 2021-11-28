/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.lab8.DAO;

import Controler.ClienteController;
import Model.ItemVenda;
import Model.Produtos;
import Model.Venda;
import bd.connection.GerenciadorConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author fernando.fernandes
 */
public class VendaDAO {

    public static boolean salvar(Venda v) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            if (v.getIdVenda() == 0) {
                instrucaoSQL = conexao.prepareStatement("INSERT INTO vendas (idCliente,data_venda,total,quantidadeItens) VALUES(?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                instrucaoSQL.setInt(1, v.getClienteVenda().getId());
                instrucaoSQL.setDate(2, v.getData());
                instrucaoSQL.setFloat(3, v.getTotal());
                instrucaoSQL.setInt(4, v.getQuantidadeItens());
            } else {
                instrucaoSQL = conexao.prepareStatement("UPDATE vendas SET idCliente = ?, data_venda = ?, total = ?, quantidadeItens = ? WHERE idVenda = ? ");

                instrucaoSQL.setInt(1, v.getClienteVenda().getId());
                instrucaoSQL.setDate(2, v.getData());
                instrucaoSQL.setFloat(3, v.getTotal());
                instrucaoSQL.setInt(4, v.getQuantidadeItens());
                instrucaoSQL.setInt(4, v.getIdVenda());
            }
            //Executar a instrução SQL
            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                if (v.getIdVenda() == 0) {
                    ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys(); //Recupero o ID do cliente
                    if (generatedKeys.next()) {
                        v.setIdVenda(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Falha ao obter o ID do produto.");
                    }
                }

                retorno = salvarProdutos(v);
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {

            //Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

//                GerenciadorConexao.fecharConexao();
                conexao.close();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static boolean excluir(int vID) {
        boolean retorno = false;
        PreparedStatement instrucaoSQL = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            //conexao = GerenciadorConexao.abrirConexao();
            Connection conexao = GerenciadorConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("DELETE FROM prod_vendas WHERE idVenda = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setInt(1, vID);

            //Mando executar a instrução SQL
            instrucaoSQL.executeUpdate();
            
            instrucaoSQL = conexao.prepareStatement("DELETE FROM vendas WHERE idVenda = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setInt(1, vID);

            //Mando executar a instrução SQL
            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {

            //Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static ArrayList<Venda> consultarVendas() {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        //Armazeno as informaçoes da tabela (resultSet) em um ArrayList
        ArrayList<Venda> listaVendas = new ArrayList<Venda>();

        try {

            conexao = GerenciadorConexao.abrirConexao();

            //Passo 3 - Executo a instrução SQL
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM vendas;");

            //Executa a Query (Consulta) - Retorna um objeto da classe ResultSet
            rs = instrucaoSQL.executeQuery();

            //Percorrer o resultSet
            while (rs.next()) {
                Venda v = new Venda();
                v.setIdVenda(rs.getInt("idVenda"));
                v.setClienteVenda(ClienteController.getClientesById(rs.getInt("idCliente")));
                v.setData(rs.getDate("data_venda"));
                v.setQuantidadeItens(rs.getInt("quantidadeItens"));
                v.setTotal(rs.getFloat("total"));

                //Adiciono na listaClientes
                listaVendas.add(v);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaVendas = null;
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                conexao.close();
                //GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listaVendas;
    }

    private static boolean salvarProdutos(Venda v) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();

            for (ItemVenda i : v.getProdutos()) {
                if (i.getIdVenda() == 0) {
                    instrucaoSQL = conexao.prepareStatement("INSERT INTO prod_vendas (idVenda,idProduto,quantidade,preco,total) VALUES(?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);

                    instrucaoSQL.setInt(1, v.getIdVenda());
                    instrucaoSQL.setInt(2, i.getIdProduto());
                    instrucaoSQL.setFloat(3, i.getQuantidade());
                    instrucaoSQL.setFloat(4, i.getPreco());
                    instrucaoSQL.setFloat(5, i.getTotal());
                } else {
                    instrucaoSQL = conexao.prepareStatement("UPDATE vendas SET id_venda = ?, id_produto = ?, quantidade = ?, preco = ?, total = ? WHERE idProdVenda = ? ");

                    instrucaoSQL.setInt(1, v.getIdVenda());
                    instrucaoSQL.setInt(2, i.getIdProduto());
                    instrucaoSQL.setFloat(3, i.getQuantidade());
                    instrucaoSQL.setFloat(4, i.getPreco());
                    instrucaoSQL.setFloat(5, i.getTotal());
                    instrucaoSQL.setInt(5, i.getIdProdVenda());
                    
                }
                //Executar a instrução SQL
                int linhasAfetadas = instrucaoSQL.executeUpdate();
                if (linhasAfetadas > 0) {
                    retorno = true;
                    if (v.getIdVenda() == 0) {
                        ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys(); //Recupero o ID do cliente
                        if (generatedKeys.next()) {
                            v.setIdVenda(generatedKeys.getInt(1));
                        } else {
                            throw new SQLException("Falha ao obter o ID do produto.");
                        }
                    }

                } else {
                    retorno = false;
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {

            //Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

//                GerenciadorConexao.fecharConexao();
                conexao.close();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

}