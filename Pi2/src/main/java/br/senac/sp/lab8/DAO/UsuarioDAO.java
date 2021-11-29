/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.lab8.DAO;

import Model.Usuario;
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
public class UsuarioDAO {

    public static boolean salvar(Usuario u) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            if (u.getIdUsuario() == 0) {
                instrucaoSQL = conexao.prepareStatement("INSERT INTO usuario (loginUsuario,senha) VALUES(?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                instrucaoSQL.setString(1, u.getLoginUsuario());
                instrucaoSQL.setString(2, u.getSenha());
            } else {
                instrucaoSQL = conexao.prepareStatement("UPDATE usuario SET loginUsuario = ?,senha = ? WHERE idUsuario =? ");

                instrucaoSQL.setString(1, u.getLoginUsuario());
                instrucaoSQL.setString(2, u.getSenha());

                instrucaoSQL.setInt(3, u.getIdUsuario());
            }
            //Executar a instrução SQL
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

//                GerenciadorConexao.fecharConexao();
                GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static boolean excluir(int uID) {
        boolean retorno = false;
        PreparedStatement instrucaoSQL = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            //conexao = GerenciadorConexao.abrirConexao();
            Connection conexao = GerenciadorConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE usuario SET desabilitado = true WHERE idUsuario =?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setInt(1, uID);

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

    public static Usuario consultarUsuarioByLogin(String login) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        //Armazeno as informaçoes da tabela (resultSet) em um ArrayList
        Usuario usuario = new Usuario();

        try {

            conexao = GerenciadorConexao.abrirConexao();

            //Passo 3 - Executo a instrução SQL
            instrucaoSQL = conexao.prepareStatement("SELECT idUsuario, loginUsuario, senha, desabilitado FROM usuario where loginUsuario = ?;");

            instrucaoSQL.setString(1, login);
            //Executa a Query (Consulta) - Retorna um objeto da classe ResultSet
            rs = instrucaoSQL.executeQuery();

            //Percorrer o resultSet
            if(rs.next()) {
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setLoginUsuario(rs.getString("loginUsuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDesabilitado(rs.getBoolean("desabilitado"));
                return usuario;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            usuario = null;
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
                //GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return usuario;
    }

    public static ArrayList<Usuario> consultarUsuario() {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        //Armazeno as informaçoes da tabela (resultSet) em um ArrayList
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

        try {

            conexao = GerenciadorConexao.abrirConexao();

            //Passo 3 - Executo a instrução SQL
            instrucaoSQL = conexao.prepareStatement("SELECT idUsuario, loginUsuario, senha, desabilitado FROM usuario;");

            //Executa a Query (Consulta) - Retorna um objeto da classe ResultSet
            rs = instrucaoSQL.executeQuery();

            //Percorrer o resultSet
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setLoginUsuario(rs.getString("loginUsuario"));
                u.setSenha(rs.getString("senha"));
                u.setDesabilitado(rs.getBoolean("desabilitado"));

                //Adiciono na listaClientes
                listaUsuarios.add(u);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaUsuarios = null;
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
                //GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listaUsuarios;
    }

    public static boolean login(String login, String senha) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {

            conexao = GerenciadorConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("SELECT idUsuario FROM usuario WHERE loginUsuario = ? and senha = ?;");
            instrucaoSQL.setString(1, login);
            instrucaoSQL.setString(2, senha);

            rs = instrucaoSQL.executeQuery();

            return rs.next();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
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

        return false;
    }

}
