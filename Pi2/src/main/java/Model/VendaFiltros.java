/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class VendaFiltros {
    private String mesVenda;
    private String cpf;
    private int idVenda;
    private Date dtInic;
    private Date dtFim;

    public VendaFiltros(String mesVenda, String cpf, int idVenda, Date dtInic, Date dtFim) {
        this.mesVenda = mesVenda;
            this.cpf = cpf;
            this.idVenda = idVenda;
            this.dtInic = dtInic;
            this.dtFim = dtFim;
    }

    public VendaFiltros() {
    }

    public String getMesVenda() {
        return mesVenda;
    }

    public void setMesVenda(String mesVenda) {
        this.mesVenda = mesVenda;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDtInic() {
        return dtInic;
    }

    public void setDtInic(Date dtInic) {
        this.dtInic = dtInic;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }
    
    
    

}
