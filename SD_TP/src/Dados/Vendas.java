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
    
    public synchronized void removerVenda(int id) throws UtilizadorJaExisteException{
        if (!items_pra_venda.containsKey(id))
            items_pra_venda.remove(id);
        else throw new UtilizadorJaExisteException();
    }
     
     public void Ver_Leiloes() {
        for (int i=0; i<items_pra_venda.size(); i++){
                Venda it= items_pra_venda.get(i);
                int id = it.getId();
                String produto = it.getNome_produto();
                String descricao = it.getDescricao();
                String cliente = it.getCliente();
                int estado = it.getEstado();
                System.out.println("Item{" + "id=" + id + "nome_produto=" + produto + ", descricao=" + descricao + ", Cliente=" + cliente + ", estado=" + estado + '}');
        }
    }

    public int size() {
        return items_pra_venda.size();
    }

    public Venda get(int i) {
        return items_pra_venda.get(i);
    }

    public void put(Integer inc, Venda item) {
        items_pra_venda.put(inc, item);
    }

    public boolean containsKey(int request) {
        return items_pra_venda.containsKey(request);
    }
}
