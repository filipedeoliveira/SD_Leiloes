/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import exceptions.UtilizadorJaExisteException;
import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Stéphane
 */
public class Vendas {

    private Lock lock;

    private Condition semPronduto;

    private TreeMap<Integer, Venda> items_pra_venda;
    //volatile static Integer inc=0; usar no sitio onde se criar as vendas

    public Vendas() {
        this.items_pra_venda = new TreeMap<>();
        this.lock = new ReentrantLock();
        this.semPronduto = this.lock.newCondition();
    }

    public Vendas(TreeMap<Integer, Venda> vendas) {
        this.items_pra_venda = vendas;
        this.lock = new ReentrantLock();
        this.semPronduto = this.lock.newCondition();
    }

    public void adicionarVenda(int id, String nome, String descricao, String cliente, int estado) throws UtilizadorJaExisteException {
        this.lock.lock();
        try {
            if (!items_pra_venda.containsKey(id)) {
                items_pra_venda.put(id, new Venda(id, nome, descricao, cliente, estado));
                semPronduto.signalAll();
            } else {
                throw new UtilizadorJaExisteException();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public synchronized void removerVenda(int id) throws UtilizadorJaExisteException {
        if (!items_pra_venda.containsKey(id)) {
            items_pra_venda.remove(id);
        } else {
            throw new UtilizadorJaExisteException();
        }
    }

    public synchronized int size() {
        return items_pra_venda.size();
    }

    public synchronized Venda get(int i) {
        return items_pra_venda.get(i);
    }

    public synchronized void put(Integer inc, Venda item) {
        items_pra_venda.put(inc, item);
    }

    public synchronized boolean containsKey(int request) {
        return items_pra_venda.containsKey(request);
    }
}
