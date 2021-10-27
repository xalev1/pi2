/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Model;

/**
 *
 * @author paulo
 */
public class Produtos {            
    private String cdFiscal;
    private String codigo;
    private String marca;
    private String nome;
    private int qtdEstoque;
    private String validade;
    private float valor;

    public String getCdFiscal() {
        return cdFiscal;
    }

    public void setCdFiscal(String cdFiscal) {
        this.cdFiscal = cdFiscal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Produtos(String cdFiscal, String codigo, String marca, String nome, int qtdEstoque, String validade, float valor) {
        this.cdFiscal = cdFiscal;
        this.codigo = codigo;
        this.marca = marca;
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.validade = validade;
        this.valor = valor;
    }
    
}
