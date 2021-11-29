/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;



/**
 *
 * @author paulo
 */
public class Usuario {

    private int idUsuario;
    private String loginUsuario;
    private String senha;
    private boolean desabilitado;

    public Usuario() {
    }

    public Usuario(int idUsuario, String loginUsuario, String senha, boolean desabilitado) {
        this.idUsuario = idUsuario;
        this.loginUsuario = loginUsuario;
        this.senha = senha;
        this.desabilitado = desabilitado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(boolean desabilitado) {
        this.desabilitado = desabilitado;
    }
    
    
}
