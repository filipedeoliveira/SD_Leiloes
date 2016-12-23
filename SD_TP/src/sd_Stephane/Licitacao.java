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
class Licitacao {
     private String cliente;
     private float valor;

    public Licitacao(String cliente, float valor) {
        this.cliente = cliente;
        this.valor = valor;
    }

     
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
     
     
}
