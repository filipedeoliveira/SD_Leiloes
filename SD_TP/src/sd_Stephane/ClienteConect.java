/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_Stephane;

import java.io.IOException;

/**
 *
 * @author Filipe Oliveira
 */
public class ClienteConect {

    public static void main(String[] args) {
        int porta;
        String ip;
        if (args.length < 2) {
            porta = 2000;
            ip = "localhost";
            System.out.println("Porta atribuida número: " + porta + "no" + ip);
        } else {
            ip = args[1];
            try {
                porta = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Erro na leitura de porta.");
                System.out.println("Porta atribuida: 2000");
                porta = 2000;
            }
        }
        try{
            Cliente2 cl = new Cliente2(porta, ip);
            //cl.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

}
