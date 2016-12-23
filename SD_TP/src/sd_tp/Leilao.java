/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_tp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filipe Oliveira
 */
public class Leilao {
    private int id_leilao;
    private List<Produto> produtos;

    
    public Leilao(){
        this.produtos= new ArrayList<Produto>();
        this.id_leilao=1;
    }
    
    public Leilao(int id_leilao, List<Produto> produtos) {
        this.id_leilao = id_leilao;
        this.produtos = produtos;
    }
    
    public Leilao(Leilao lei){
        this.id_leilao=getId_leilao();
        this.produtos=getProdutos();
    }
    
    public void inserirProduto(int idCliente,Produto p){
        
    }
    
    
    
    public int getId_leilao() {
        return id_leilao;
    }

    public void setId_leilao(int id_leilao) {
        this.id_leilao = id_leilao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
}
