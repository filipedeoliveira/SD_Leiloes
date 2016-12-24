/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_Stephane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Filipe Oliveira
 */
public class Cliente2 {

    private Socket clienteSck;
    private BufferedReader input;
    private PrintWriter output;

    public Cliente2(int porta, String ip) throws IOException {
        try {
            clienteSck = new Socket(ip, porta);
        } catch (java.net.ConnectException e) {
            throw new IOException("Servidor não está ligado!");
        }

        input = new BufferedReader(new InputStreamReader(clienteSck.getInputStream()));
        output = new PrintWriter(clienteSck.getOutputStream(), true);

    }
    
    //tartar aqui dos splits, recer o comando e meter no outup para enviar ao servidor

}
