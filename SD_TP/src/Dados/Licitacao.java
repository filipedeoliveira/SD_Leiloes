/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

/**
 *
 * @author Stéphane
 */
class Licitacao {
    int id;
     private String cliente;
     private int valor;

    public Licitacao(int id,String cliente, int valor) {
        this.id = id;
        this.cliente = cliente;
        this.valor = valor;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

     
    public synchronized String getCliente() {
        return cliente;
    }

    public synchronized void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public synchronized int getValor() {
        return valor;
    }

    public synchronized void setValor(int valor) {
        this.valor = valor;
    }
     
     
}
