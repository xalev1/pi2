/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author paulo
 */
public class Venda {
    private Clientes clienteVenda;
    //trocar por produto venda
    private ArrayList<Produtos> produtos;
    private Date data;
    private float total;
    private int quantidadeItens;

    public Venda(Clientes clienteVenda, ArrayList<Produtos> produtos, Date data, float total, int quantidadeItens) {
        this.clienteVenda = clienteVenda;
        this.produtos = produtos;
        this.data = data;
        this.total = total;
        this.quantidadeItens = quantidadeItens;
    }

    public Clientes getClienteVenda() {
        return clienteVenda;
    }

    public void setClienteVenda(Clientes clienteVenda) {
        this.clienteVenda = clienteVenda;
    }

    public ArrayList<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produtos> produtos) {
        this.produtos = produtos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }
    
    

}
