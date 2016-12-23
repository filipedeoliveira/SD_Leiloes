/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_Stephane;

/**
 *
 * @author Stéphane
 */
public class Item {
        String nome_produto;
        String descricao;
        String Cliente;
        int estado; //0 - não vendeu, 1 - vendido

    public Item(String nome_produto, String descricao, String Cliente, int estado) {
        
        this.nome_produto = nome_produto;
        this.descricao = descricao;
        this.Cliente = Cliente;
        this.estado = estado;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    public String toString(Item i) {
        String result = "Item{" + "nome_produto=" + i.getNome_produto() + ", descricao=" + i.getDescricao() + ", Cliente=" + i.getCliente() + ", estado=" + i.getEstado() + '}';
        return result;
    }
   
        
        
        
}
