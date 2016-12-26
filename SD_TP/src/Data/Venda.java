/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Stéphane
 */
public class Venda {
        int id;
        String nome_produto;
        String descricao;
        String Cliente;
        int estado; //0 - não vendeu, 1 - vendido

    public Venda(int id,String nome_produto, String descricao, String Cliente, int estado) {
        this.id= id;
        this.nome_produto = nome_produto;
        this.descricao = descricao;
        this.Cliente = Cliente;
        this.estado = estado;
    }

    public synchronized String getNome_produto() {
        return nome_produto;
    }

    public synchronized void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public synchronized String getDescricao() {
        return descricao;
    }

    public synchronized void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public synchronized String getCliente() {
        return Cliente;
    }

    public synchronized void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public synchronized int getEstado() {
        return estado;
    }

    public synchronized void setEstado(int estado) {
        this.estado = estado;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    
  
        
        
}
