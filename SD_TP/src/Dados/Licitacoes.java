/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import exceptions.UtilizadorJaExisteException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Stéphane
 */
public class Licitacoes {

   private Condition semLicitacao;
    
    private TreeMap<Integer, ArrayList<Licitacao>> licitacoes;
    private Lock lock;

    

    public Licitacoes() {
        this.licitacoes = new TreeMap<>();
        this.lock = new ReentrantLock();
        this.semLicitacao = this.lock.newCondition();
    }

    public Licitacoes(TreeMap<Integer, ArrayList<Licitacao>> licitacoes1) {
        this.licitacoes = licitacoes1;
        this.lock = new ReentrantLock();
        this.semLicitacao = this.lock.newCondition();
    }

    public void adicionarLicitacao(int id, String nome, int valor) {
        this.lock.lock();
        try {
            if (!licitacoes.containsKey(id)) {
                ArrayList<Licitacao> new_vector = new ArrayList<Licitacao>();
                new_vector.add(new Licitacao(id, nome, valor));
                licitacoes.put(id, new_vector);
                semLicitacao.signalAll();
            } else {
                ArrayList<Licitacao> new_vector = licitacoes.get(id);
                new_vector.add(new Licitacao(id, nome, valor));
                semLicitacao.signalAll();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public synchronized void removerLicitacao(int id, Licitacao l) throws UtilizadorJaExisteException {
        if (!licitacoes.containsKey(id)) {
            licitacoes.remove(id, l);
        } else {
            throw new UtilizadorJaExisteException();
        }
    }

    public synchronized ArrayList<Licitacao> get(int value) {
        return licitacoes.get(value);
    }

    public boolean containsKey(int id) {
        return licitacoes.containsKey(id);
    }

    public static synchronized String maiorLicitacao2(int id, String name, Licitacoes licit) {
        String result = "";
        if (licit.containsKey(id)) {
            ArrayList<Licitacao> lis = licit.get(id);
            float maior = 0;
            String vencedor = "";
            for (int j = 0; j < lis.size(); j++) {
                if (lis.get(j).getValor() > maior) {
                    maior = lis.get(j).getValor();
                    vencedor = lis.get(j).getCliente();
                }
            }
            if (vencedor.equals(name)) {
                result = "+";
            }
        }
        return result;
    }
}
