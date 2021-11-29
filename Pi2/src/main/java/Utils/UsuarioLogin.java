/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Usuario;

/**
 *
 * @author fernando.fernandes
 */
public class UsuarioLogin {
    private static UsuarioLogin instance;
    public Usuario user;
    
    private UsuarioLogin(Usuario user){
        this.user = user;
    }
     public static UsuarioLogin getInstance(Usuario value) {
        if (instance == null) {
            instance = new UsuarioLogin(value);
        }
        return instance;
    }

}
