/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_Stephane;

/**
 *
 * @author Filipe Oliveira
 */
public class myException extends Exception {
    
    protected String message;
    
    public myException(String message){
        super(message);
        this.message=message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
