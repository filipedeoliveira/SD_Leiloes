/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Filipe Oliveira
 */
public class NaoExisteItensException extends Exception {
    public NaoExisteItensException(){
        super();
    }
    
    public NaoExisteItensException(String s){
        super(s);
    }
    
}
