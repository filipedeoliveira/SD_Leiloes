/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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
    
    public synchronized void adicionarLicitacao(int id, String nome, int valor) throws UtilizadorJaExisteException{
        if (!licitacoes.containsKey(id))
            licitacoes.put(id, new Licitacao(id,nome,valor));
        else throw new UtilizadorJaExisteException();
    }
}
