/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Venda;
import Model.VendaFiltros;
import br.senac.sp.lab8.DAO.VendaDAO;
import java.util.ArrayList;


public class VendaController {
    
    public static boolean salvar(Venda ven)
    {
        return VendaDAO.salvar(ven);
    }
    
    public static ArrayList<Venda> getVendas(){
        return VendaDAO.consultarVendas();
    }
    
    public static ArrayList<Venda> getVendas(VendaFiltros vend){
        return VendaDAO.consultarVendas(vend);
    }
    
    
    public static Venda getVendas(int id){
        ArrayList<Venda> vendaList = VendaDAO.consultarVendasById(id);
        if(!vendaList.isEmpty()){
            
            return VendaDAO.consultarVendasById(id).get(0);
        }
        return null;
    }
    
    
    public static boolean excluir(int id){
        return VendaDAO.excluir(id);
    }
}
