/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Clientes;
import br.senac.sp.lab8.DAO.ClienteDAO;
import java.util.ArrayList;


/**
 *
 * @author fernando.fernandes
 */
public class ClienteController {
    
    public static boolean salvar(Clientes cliente)
    {
        return ClienteDAO.salvar(cliente);
    }
    
    public static ArrayList<Clientes> getClientes(){
        return ClienteDAO.consultarClientes();
    }
    
    public static Clientes getClientesById(int id){
        Clientes cliente = new Clientes();
        cliente.setId(id);
        ArrayList<Clientes> listClientes = ClienteDAO.consultarClientes(cliente, "id");
        Clientes cli = null;
        cli = listClientes.get(0);
        return cli;
    }
    
    public static Clientes getClientesByCPF(Clientes cliente){
        ArrayList<Clientes> listClientes = ClienteDAO.consultarClientes(cliente, "cpf");
        Clientes cli = null;
        cli = listClientes.get(0);
        return cli;
    }
    
    public static Clientes getClientesByNome(Clientes cliente){
        ArrayList<Clientes> listClientes = ClienteDAO.consultarClientes(cliente, "nome");
        Clientes cli = null;
        cli = listClientes.get(0);
        return cli;
    }
    public static boolean excluir(int id){
        return ClienteDAO.excluir(id);
    }
}
