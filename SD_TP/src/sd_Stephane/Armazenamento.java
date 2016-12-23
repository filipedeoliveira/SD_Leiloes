/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_Stephane;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Stéphane
 */
public class Armazenamento {
    static HashMap<Integer,Item> items_pra_venda;
    static HashMap<Integer,ArrayList<Licitacao>> licitacoes;
    static HashMap<String,String> clientes;

    public Armazenamento() {
        this.items_pra_venda = new HashMap<Integer,Item> ();
        this.licitacoes = new HashMap<Integer,ArrayList<Licitacao>>();
        this.clientes = new HashMap<String,String>();
       
    }
    
    public void registar_Cliente(String user, String pass){
        this.clientes.put(user,pass);
    }
    
    public void remove_Cliente(String user){
        this.clientes.remove(user);
    }
    
    public void registar_Venda(int i, Item it){
        this.items_pra_venda.put(i,it);
        
    }
    public void remove_Venda(int i){
        this.items_pra_venda.remove(i);
    }
    
    
    public void remove_Licitacoes(int i){
        this.licitacoes.remove(i);
    }
    
    public HashMap<Integer, Item> getItems_pra_venda() {
        return items_pra_venda;
    }

    public void setItems_pra_venda(HashMap<Integer, Item> items_pra_venda) {
        this.items_pra_venda = items_pra_venda;
    }

    public static HashMap<Integer, ArrayList<Licitacao>> getLicitacoes() {
        return licitacoes;
    }

    public static void setLicitacoes(HashMap<Integer, ArrayList<Licitacao>> licitacoes) {
        Armazenamento.licitacoes = licitacoes;
    }

    

    public HashMap<String, String> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, String> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Armazenamento{" + "items_pra_venda=" + items_pra_venda + '}';
    }
    
    
   
    
    
}
