/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import exceptions.UtilizadorJaExisteException;
import java.util.TreeMap;

/**
 *
 * @author Stéphane
 */
public class Vendas {
 
    private TreeMap<Integer,Venda> items_pra_venda;
    //volatile static Integer inc=0; usar no sitio onde se criar as vendas
    
    public Vendas(){
        this.items_pra_venda = new TreeMap<>();
    }
    
    public Vendas (TreeMap<Integer, Venda> vendas){
        this.items_pra_venda = vendas;
    }
    
    public synchronized void adicionarVenda(int id, String nome, String descricao, String cliente, int estado) throws UtilizadorJaExisteException{
        if (!items_pra_venda.containsKey(id))
            items_pra_venda.put(id, new Venda(id,nome, descricao, cliente, estado));
        else throw new UtilizadorJaExisteException();
    }
}