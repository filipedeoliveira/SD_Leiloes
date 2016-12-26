/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Data.Cliente;
import Data.Clientes;
import Data.Licitacoes;
import exceptions.UtilizadorNaoExisteException;
import exceptions.UtilizadorOfflineException;
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

    public ServerRunnable(Socket cliente, Clientes clientes, Licitacoes licitacoes) throws IOException {
        this.nome = null;
        this.clienteSock = cliente;
        this.cliente = null;
        this.clientes = clientes;
        this.licitacoes = licitacoes;
        this.outputServer = new PrintWriter(clienteSock.getOutputStream());
        this.inputClient = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

    }

    @Override
    public void run() {

        try {
            String mensage;
            this.outputServer = new PrintWriter(clienteSock.getOutputStream());
            this.inputClient = new BufferedReader(new InputStreamReader(clienteSock.getInputStream()));

            while ((mensage = inputClient.readLine()) != null) {
                System.out.println("->" + clienteSock.getPort() + mensage);
                this.comandos(mensage);
            }

        } catch (IOException e) {
            System.out.println("O cliente " + clienteSock.getPort() + "saiu do sistema!");
            System.out.println("Logout executado! Até breve!");
            if (this.nome != null) {
                try {
                    clientes.logOut(this.nome);
                } catch (UtilizadorNaoExisteException | UtilizadorOfflineException ex1) {
                }
            }

        } finally {
            outputServer.close();
        }

    }

    public void comandos(String comando) {

    }

}
