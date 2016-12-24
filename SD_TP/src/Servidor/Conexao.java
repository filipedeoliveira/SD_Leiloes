/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Filipe Oliveira
 */
public class Conexao {

    private Socket cs;
    private BufferedReader input;
    private PrintWriter output;

    public Conexao(Socket sock) throws IOException {
        this.cs = sock;
        this.input = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        this.output = new PrintWriter(cs.getOutputStream(), true);
    }

    public String leMensagem() throws IOException {
        return input.readLine();
    }

    public void enviaMensagem(String ms) {
        output.println(ms);
    }
}
