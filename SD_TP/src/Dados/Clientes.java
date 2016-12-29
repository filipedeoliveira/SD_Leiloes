/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;
import exceptions.*;
import java.util.Collection;
import java.util.TreeMap;
/**
 *
 * @author Stéphane
 */
public class Clientes {
    private TreeMap<String, Cliente> utilizadores;
    
    
    public Clientes(){
        this.utilizadores = new TreeMap<>();
    }
    
    public Clientes(TreeMap<String, Cliente> utilizadores){
        this.utilizadores = utilizadores;
    }
 
    
     public synchronized void adicionarCliente(String nome, String password) throws UtilizadorJaExisteException{
        if (!utilizadores.containsKey(nome))
            utilizadores.put(nome, new Cliente(nome, password));
        else throw new UtilizadorJaExisteException();
    }
    
    
     public synchronized Cliente logIn(String nome, String password) throws UtilizadorNaoExisteException, UtilizadorOnlineException, PasswordIncorretaException{
        Cliente u;
        if (utilizadores.containsKey(nome)){
            u = utilizadores.get(nome);
            // Método "logIn" da classe Utilizador é synchronized
            u.logIn(password);
        }
        else throw new UtilizadorNaoExisteException();
        return u;
    }
    
       
    public synchronized void logOut(String nome) throws UtilizadorNaoExisteException, UtilizadorOfflineException{
        if (utilizadores.containsKey(nome)){
            Cliente u = utilizadores.get(nome);
            // Método "logOut" da classe Utilizador é synchronized
            u.logOut();
        }
        else throw new UtilizadorNaoExisteException();
    }
    
}
