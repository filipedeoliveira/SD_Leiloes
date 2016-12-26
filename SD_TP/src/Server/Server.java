/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Data.Clientes;
import Data.Licitacoes;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Filipe Oliveira
 */
public class Server {

    private static Clientes clientes;
    private static Licitacoes licitacoes;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2048);
            clientes = new Clientes();
            licitacoes = new Licitacoes();

            while (true) {
                Socket cliente = server.accept();
                ServerRunnable clientHandler = new ServerRunnable(cliente, clientes, licitacoes);
                Thread t = new Thread((Runnable) clientHandler);
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Falha na conexão!!");
        }
    }

}
