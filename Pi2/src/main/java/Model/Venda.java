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
public class Venda {
    private int idVenda;
    private int clienteVenda;
    //trocar por produto venda
    private ArrayList<ItemVenda> produtos;
    private Date data;
    private float total;
    private int quantidadeItens;
    private Usuario usuarioVenda;

    public Venda(int idVenda, int clienteVenda, ArrayList<ItemVenda> produtos, Date data, float total, int quantidadeItens, Usuario idUsuarioVenda) {
        this.idVenda = idVenda;
        this.clienteVenda = clienteVenda;
        this.produtos = produtos;
        this.data = data;
        this.total = total;
        this.quantidadeItens = quantidadeItens;
        this.usuarioVenda = idUsuarioVenda;
    }

    public Venda() {
    }

    
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getClienteVenda() {
        return clienteVenda;
    }

    public void setClienteVenda(int clienteVenda) {
        this.clienteVenda = clienteVenda;
    }

    public ArrayList<ItemVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ItemVenda> produtos) {
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

    public Usuario getUsuarioVenda() {
        return usuarioVenda;
    }

    public void setUsuarioVenda(Usuario idUsuarioVenda) {
        this.usuarioVenda = idUsuarioVenda;
    }

    
    
    public float totaliza(){
        float total=0;
        for (ItemVenda produto : produtos) {
            total+= produto.getTotal();
        }
        return total;
    }

    public void adicionaItem() {
        this.quantidadeItens++;
    }

    public void removerItem() {
        this.quantidadeItens--;
    }
    

}
