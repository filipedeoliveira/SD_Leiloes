/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;
import exceptions.PasswordIncorretaException;
import exceptions.UtilizadorOfflineException;
import exceptions.UtilizadorOnlineException;
/**
 *
 * @author Stéphane
 */

public class Cliente {
    
    private String username;
    private String password;
    private boolean login;
    
    public Cliente(String user,String pass){
        this.username=user;
        this.password=pass;
        this.login = false;
    } 
    
    public Cliente( Cliente cl){
        this.username = cl.username;
        this.password = cl.password;
        this.login = cl.login;
    }

    public synchronized String getUsername() {
        return username;
    }

    public synchronized void setUsername(String username) {
        this.username = username;
    }

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void setPassword(String password) {
        this.password = password;
    }
    
     public synchronized void logIn(String password) throws UtilizadorOnlineException, PasswordIncorretaException{
        if (this.login) throw new UtilizadorOnlineException();
        else if (!this.password.equals(password)) throw new PasswordIncorretaException();
        else this.login = true;
    }
    
    public synchronized void logOut() throws UtilizadorOfflineException{
        if (!this.login) throw new UtilizadorOfflineException();
        else this.login = false;
    }
    
    
}
