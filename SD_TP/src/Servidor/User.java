/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.Serializable;

/**
 *
 * @author Filipe Oliveira
 */
public class User implements Serializable {

    private String username;
    private String password;
    private boolean login;

    public User() {
        this.username = "";
        this.password = "";
        this.login = false;
    }

    public User(String name, String psw) {
        this.username = name;
        this.password = psw;
        this.login = false;
    }

    public User(User us) {
        this.username = us.getUsername();
        this.password = us.getPassword();
        this.login = us.getLogin();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void login() {
        this.login = true;
    }

    public void logout() {
        this.login = false;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", login=" + login + '}';
    }
    
    

}
