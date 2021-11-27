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
public class Produtos {

    private int idProduto;
    private String cdFiscal;
    private String marca;
    private String nome;
    private float qtdEstoque;
    private Date validade;
    private float valor;
    private String tipo;
    private String descricao;

    public Produtos() {
    }

    public Produtos(int idProduto, String cdFiscal, String marca, String nome, float qtdEstoque, Date validade, float valor, String tipo, String descricao) {
        this.idProduto = idProduto;
        this.cdFiscal = cdFiscal;
        this.marca = marca;
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.validade = validade;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
    }
    
 

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCdFiscal() {
        return cdFiscal;
    }

    public void setCdFiscal(String cdFiscal) {
        this.cdFiscal = cdFiscal;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(float qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }


}
