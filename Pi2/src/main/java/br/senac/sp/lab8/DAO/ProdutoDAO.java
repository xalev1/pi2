/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.lab8.DAO;

import Model.Produtos;
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
public class ProdutoDAO {

    public static boolean salvar(Produtos p) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            if (p.getIdProduto() == 0) {
                instrucaoSQL = conexao.prepareStatement("INSERT INTO produtos (cdFiscal,marca,nome,tipo,qtdEstoque,validade,valor,descricao) VALUES(?,?,?,?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                instrucaoSQL.setString(1, p.getCdFiscal());
                instrucaoSQL.setString(2, p.getMarca());
                instrucaoSQL.setString(3, p.getNome());
                instrucaoSQL.setString(4, p.getTipo());
                instrucaoSQL.setFloat(5, p.getQtdEstoque());
                instrucaoSQL.setDate(6, p.getValidade());
                instrucaoSQL.setFloat(7, p.getValor());
                instrucaoSQL.setString(8, p.getDescricao());
            } else {
                instrucaoSQL = conexao.prepareStatement("UPDATE produtos SET cdFiscal = ?, marca = ?, nome = ?, tipo = ?, qtdEstoque = ?, validade = ?, valor = ?, descricao = ? WHERE idProduto =? ");

                instrucaoSQL.setString(1, p.getCdFiscal());
                instrucaoSQL.setString(2, p.getMarca());
                instrucaoSQL.setString(3, p.getNome());
                instrucaoSQL.setString(4, p.getTipo());
                instrucaoSQL.setFloat(5, p.getQtdEstoque());
                instrucaoSQL.setDate(6, p.getValidade());
                instrucaoSQL.setFloat(7, p.getValor());
                instrucaoSQL.setString(8, p.getDescricao());
                instrucaoSQL.setInt(9, p.getIdProduto());

            }
            //Executar a instrução SQL
            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;

//                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys(); //Recupero o ID do cliente
//                if (generatedKeys.next()) {
//                    p.setIdProduto(generatedKeys.getInt(1));
//                } else {
//                    throw new SQLException("Falha ao obter o ID do produto.");
//                }
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

    public static boolean excluir(int pID) {
        boolean retorno = false;
        PreparedStatement instrucaoSQL = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            //conexao = GerenciadorConexao.abrirConexao();
            Connection conexao = GerenciadorConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("DELETE FROM produtos WHERE idProduto = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setInt(1, pID);

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

    public static ArrayList<Produtos> consultarProdutos() {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        //Armazeno as informaçoes da tabela (resultSet) em um ArrayList
        ArrayList<Produtos> listaProdutos = new ArrayList<Produtos>();

        try {

            conexao = GerenciadorConexao.abrirConexao();

            //Passo 3 - Executo a instrução SQL
            instrucaoSQL = conexao.prepareStatement("SELECT idProduto,nome,qtdEstoque FROM produtos;");

            //Executa a Query (Consulta) - Retorna um objeto da classe ResultSet
            rs = instrucaoSQL.executeQuery();

            //Percorrer o resultSet
            while (rs.next()) {
                Produtos p = new Produtos();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setQtdEstoque(rs.getFloat("qtdEstoque"));

                //Adiciono na listaClientes
                listaProdutos.add(p);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaProdutos = null;
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

        return listaProdutos;
    }

    public static ArrayList<String> consultarTipos() {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ArrayList<String> listaTipo = new ArrayList<String>();

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT tipo FROM produtos group by tipo;");
            rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                listaTipo.add(rs.getString("tipo"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaTipo = null;
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

        return listaTipo;
    }

    public static ArrayList<Produtos> consultarProdutos(Produtos pro, String campo) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ArrayList<Produtos> listaClientes = new ArrayList<Produtos>();

        try {

            conexao = GerenciadorConexao.abrirConexao();

            switch (campo) {
                case "nome":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ?;");
                    instrucaoSQL.setString(1, "%" + pro.getNome() + '%');
                    break;
                case "cdFiscal":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM produtos WHERE cdFiscal = ?;");
                    instrucaoSQL.setString(1, pro.getCdFiscal());
                    break;
                case "tipo":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM produtos WHERE tipo = ?;");
                    instrucaoSQL.setString(1, pro.getTipo());
                    break;
                case "id":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM produtos WHERE idProduto = ?;");
                    instrucaoSQL.setInt(1, pro.getIdProduto());
                    break;
                case "marca":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM produtos WHERE marca like ?;");
                    instrucaoSQL.setString(1, "%" + pro.getMarca() + "%");
                    break;
            }

            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Produtos c = new Produtos(
                        rs.getInt("idProduto"),
                        rs.getString("cdFiscal"),
                        rs.getString("marca"),
                        rs.getString("nome"),
                        rs.getFloat("qtdEstoque"),
                        rs.getDate("validade"),
                        rs.getFloat("valor"),
                        rs.getString("tipo"),
                        rs.getString("descricao")
                );
                listaClientes.add(c);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaClientes = null;
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listaClientes;
    }

}
