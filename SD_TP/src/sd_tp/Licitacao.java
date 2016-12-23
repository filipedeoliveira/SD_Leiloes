/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_tp;

/**
 *
 * @author Filipe Oliveira
 */
public class Licitacao {
    private Cliente cl;
    private float valor;

    
    
    public Licitacao(){
        this.cl= new Cliente();
        this.valor=(float) 0.0;
    }
    
    public Licitacao(Cliente cl, float valor) {
        this.cl = cl;
        this.valor = valor;
    }
    
    public Licitacao(Licitacao lc){
        this.cl=getCl();
        this.valor=getValor();
    }

    
    
    public Cliente getCl() {
        return cl;
    }

    public void setCl(Cliente cl) {
        this.cl = cl;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
