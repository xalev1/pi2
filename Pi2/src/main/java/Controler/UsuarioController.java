/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Usuario;
import br.senac.sp.lab8.DAO.UsuarioDAO;
import java.util.ArrayList;

public class UsuarioController {

    public static boolean salvar(Usuario usu) {
        return UsuarioDAO.salvar(usu);
    }

    public static ArrayList<Usuario> getUsuarioList() {
        return UsuarioDAO.consultarUsuario();
    }

    public static boolean login(String login, String senha) {
        return UsuarioDAO.login(login, senha);
    }

    public static boolean excluir(int id) {
        return UsuarioDAO.excluir(id);
    }
    
    public static Usuario consultarUsuarioByLogin(String login) {
        return UsuarioDAO.consultarUsuarioByLogin(login);
    }
}
