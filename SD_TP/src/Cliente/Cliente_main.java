/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Stéphane
 */
public class Cliente_main {

    private static Socket cliente;
    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader stdIn;

    public static void main(String[] args) throws Exception {

        try {
            String comando, resposta;
            cliente = new Socket("localhost", 2048);
            out = new PrintWriter(cliente.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Bem vindo");
            Menu.mostra_menu1();

            System.out.print("> ");
            // Lê um comando do teclado

            while (!(comando = stdIn.readLine()).equals("8")) {

                // Envia o comando ao servidor
                out.println(comando);
                out.flush();

                
               resposta = in.readLine();
                switch (resposta) {
                    
                    case "DECORRER": 
                        while (!("###".equals(resposta))) {
                            System.out.println(resposta);
                            resposta = in.readLine();
                        }
                        break;
                        case "ATIVOS": 
                        while (!("###".equals(resposta))) {
                            System.out.println(resposta);
                            resposta = in.readLine();
                        }
                        break;
                    case "MENSAGENS": 
                        while (!("###".equals(resposta))) {
                            System.out.println(resposta);
                            resposta = in.readLine();
                        }
                        break;
                    default: {
                        System.out.println(resposta);
                        System.out.print("> ");
                    }
                }

            }

            System.out.println("Acabou de sair do sistema, obrigado pela visita!");

            // Envia a mensagem "sair" ao servidor para efetuar o logout do utilizador
            out.println(comando);
            out.flush();

            cliente.shutdownInput();
            cliente.shutdownOutput();
            cliente.close();

        } catch (Exception e) {
            System.out.println("Servidor offline!");
        }

    }
}
