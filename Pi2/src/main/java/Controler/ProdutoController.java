/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Produtos;
import br.senac.sp.lab8.DAO.ProdutoDAO;
import java.util.ArrayList;


/**
 *
 * @author fernando.fernandes
 */
public class ProdutoController {
    
    public static boolean salvar(Produtos prod)
    {
        return ProdutoDAO.salvar(prod);
    }
    
    public static ArrayList<Produtos> getProdutos(){
        return ProdutoDAO.consultarProdutos();
    }
    
    public static Produtos getProdutosById(int id){
        Produtos produto = new Produtos();
        produto.setIdProduto(id);
        ArrayList<Produtos> listProdutos = ProdutoDAO.consultarProdutos(produto, "id");
        Produtos prod = null;
        prod = listProdutos.get(0);
        return prod;
    }
    
    public static ArrayList<Produtos> getProdutosByTipo(Produtos produto){
        ArrayList<Produtos> listProdutos = ProdutoDAO.consultarProdutos(produto, "tipo");
        return listProdutos;
    }
    
    public static ArrayList<Produtos> getProdutosByNome(Produtos produto){
        ArrayList<Produtos> listProdutos = ProdutoDAO.consultarProdutos(produto, "nome");
        return listProdutos;
    }
    
    public static ArrayList<Produtos> getProdutosByCdFiscal(Produtos produto){
        ArrayList<Produtos> listProdutos = ProdutoDAO.consultarProdutos(produto, "cdFiscal");
        return listProdutos;
    }

    public static ArrayList<Produtos> getProdutosByMarca(Produtos produto){
        ArrayList<Produtos> listProdutos = ProdutoDAO.consultarProdutos(produto, "marca");
        return listProdutos;
    }
    
    public static boolean excluir(int id){
        return ProdutoDAO.excluir(id);
    }
    
    public static ArrayList<String> getTipos(){
        ArrayList<String> listTipos = ProdutoDAO.consultarTipos();
        return listTipos;
    }
    
}
