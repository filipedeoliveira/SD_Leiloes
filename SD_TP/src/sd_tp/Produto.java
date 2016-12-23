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
public class Produto {
    private int idProduto;
    private float preco;
    private String descricao;


    public Produto(){
        this.idProduto=0;
        this.preco=(float) 0.0;
        this.descricao="";
    }
    
    public Produto(int idProduto, float preco, String descricao) {
        this.idProduto = idProduto;
        this.preco = preco;
        this.descricao = descricao;
    }
    
    public Produto(Produto p){
        this.idProduto=p.getIdProduto();
        this.preco=p.getPreco();
        this.descricao=p.getDescricao();
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
        
}
