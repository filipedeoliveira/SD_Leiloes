/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Data.Cliente;
import Data.Clientes;
import Data.Licitacoes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Filipe Oliveira
 */
public class ServerRunnable implements Runnable {

    private String nome;
    private Socket clienteSock;
    private Cliente cliente;
    private PrintWriter outputServer;
    private BufferedReader inputClient;
    
    private Clientes clientes;
    private Licitacoes licitacoes;

    public ServerRunnable(Socket cliente, Clientes clientes, Licitacoes licitacoes) throws IOException{
        this.nome=null;
        this.clienteSock=cliente;
        this.cliente=null;
        this.clientes=clientes;
        this.licitacoes=licitacoes;
        this.outputServer = new PrintWriter(clienteSock.getOutputStream());
        this.inputClient = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        
    }
    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
