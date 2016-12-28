/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import exceptions.UtilizadorJaExisteException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Stéphane
 */
public class Licitacoes {
    private TreeMap<Integer,ArrayList<Licitacao>> licitacoes;
    
    public Licitacoes(){
        this.licitacoes = new TreeMap<>();
    }
    
    public Licitacoes (TreeMap<Integer, ArrayList<Licitacao>> licitacoes1){
        this.licitacoes = licitacoes1;
    }
    
    public synchronized void adicionarLicitacao(int id, String nome, int valor) {
        if (!licitacoes.containsKey(id)){
            ArrayList<Licitacao> new_vector  =  new ArrayList<Licitacao>();
            new_vector.add(new Licitacao(id,nome,valor));
            licitacoes.put(id,new_vector);
        }
        else {ArrayList<Licitacao> new_vector = licitacoes.get(id);
              new_vector.add(new Licitacao(id,nome,valor));}
    }
    
    public synchronized void removerLicitacao(int id, Licitacao l) throws UtilizadorJaExisteException{
        if (!licitacoes.containsKey(id))
            licitacoes.remove(id,l);
        
        else throw new UtilizadorJaExisteException();
    }

    public ArrayList<Licitacao> get(int value) {
    return licitacoes.get(value);
    }

    public boolean containsKey(int id) {
        return licitacoes.containsKey(id);
    }
    
    
}
