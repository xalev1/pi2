/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author paulo
 */
public class ItemVenda {
    private int idProdVenda;
    private int idVenda;
    private int idProduto;
    private float quantidade;
    private float preco;
    private float total;    

    public ItemVenda() {
    }

    public ItemVenda(int idProdVenda, int idVenda, int idProduto, float quantidade, float preco, float total) {
        this.idProdVenda = idProdVenda;
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.total = total;
    }

    public int getIdProdVenda() {
        return idProdVenda;
    }

    public void setIdProdVenda(int idProdVenda) {
        this.idProdVenda = idProdVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
