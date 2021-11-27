/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.lab8.DAO;

import Model.Clientes;
import bd.connection.GerenciadorConexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author fernando.fernandes
 */
public class ClienteDAO {

    public static boolean salvar(Clientes p) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            if (p.getId() == 0) {
                instrucaoSQL = conexao.prepareStatement("INSERT INTO clientes (nomeCompleto,CPF,nascimento,endereco,cidade,bairro,cep,contato,email,observacao,sexo) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                instrucaoSQL.setString(1, p.getNomeCompleto());
                instrucaoSQL.setString(2, p.getCPF());
                instrucaoSQL.setDate(3, p.getNascimento());
                instrucaoSQL.setString(4, p.getEndereco());
                instrucaoSQL.setString(5, p.getCidade());
                instrucaoSQL.setString(6, p.getBairro());
                instrucaoSQL.setString(7, p.getCep());
                instrucaoSQL.setString(8, p.getContato());
                instrucaoSQL.setString(9, p.getEmail());
                instrucaoSQL.setString(10, p.getObservacao());
                instrucaoSQL.setString(11, p.getSexo());
            } else {
                instrucaoSQL = conexao.prepareStatement("UPDATE clientes SET nomeCompleto = ?,CPF = ?,nascimento = ?,endereco = ?,cidade = ?,bairro = ?,cep = ?,contato = ?,email = ?,observacao = ?,sexo = ? WHERE idCliente =? ");

                instrucaoSQL.setString(1, p.getNomeCompleto());
                instrucaoSQL.setString(2, p.getCPF());
                
                instrucaoSQL.setDate(3, p.getNascimento());
                instrucaoSQL.setString(4, p.getEndereco());
                instrucaoSQL.setString(5, p.getCidade());
                instrucaoSQL.setString(6, p.getBairro());
                instrucaoSQL.setString(7, p.getCep());
                instrucaoSQL.setString(8, p.getContato());
                instrucaoSQL.setString(9, p.getEmail());
                instrucaoSQL.setString(10, p.getObservacao());
                instrucaoSQL.setString(11, p.getSexo());
                instrucaoSQL.setInt(12, p.getId());
            }
            //Executar a instrução SQL
            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;

                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys(); //Recupero o ID do cliente
                if (generatedKeys.next()) {
                    p.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do cliente.");
                }
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

            instrucaoSQL = conexao.prepareStatement("DELETE FROM clientes WHERE idCliente = ?");

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

    public static ArrayList<Clientes> consultarClientes() {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        //Armazeno as informaçoes da tabela (resultSet) em um ArrayList
        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();

        try {

            conexao = GerenciadorConexao.abrirConexao();

            //Passo 3 - Executo a instrução SQL
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM clientes;");

            //Executa a Query (Consulta) - Retorna um objeto da classe ResultSet
            rs = instrucaoSQL.executeQuery();

            //Percorrer o resultSet
            while (rs.next()) {
                Clientes c = new Clientes();
                c.setId(rs.getInt("idcliente"));
                c.setNomeCompleto(rs.getString("nome"));
                c.setCPF(rs.getString("CPF"));

                //Adiciono na listaClientes
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

                conexao.close();
                //GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listaClientes;
    }

    public static ArrayList<Clientes> consultarClientes(Clientes cli, String campo) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();

        try {

            conexao = GerenciadorConexao.abrirConexao();

            switch (campo) {
                case "nome":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE nomeCompleto LIKE ?;");
                    instrucaoSQL.setString(1, "%" + cli.getNomeCompleto() + '%');
                    break;
                case "id":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE idCliente = ?;");
                    instrucaoSQL.setInt(1, cli.getId());
                    break;
                case "cpf":
                    instrucaoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE cpf = ?;");
                    instrucaoSQL.setString(1, cli.getCPF());
                    break;
            }

            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Clientes c = new Clientes(rs.getInt("idCliente"),
                        rs.getString("NomeCompleto"),
                        rs.getString("CPF"),
                        rs.getDate("Nascimento"),
                        rs.getString("Endereco"),
                        rs.getString("Bairro"),
                        rs.getString("cidade"),
                        rs.getString("cep"),
                        rs.getString("Contato"),
                        rs.getString("email"),
                        rs.getString("Observacao"),
                        rs.getString("Sexo"));
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
