/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_Stephane;

/**
 *
 * @author Stéphane
 */
public class Cliente {
    
    private String username;
    private String password;
    
    public Cliente(String user,String pass){
        this.username=user;
        this.password=pass;
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

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
