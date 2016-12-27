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

            while (!(comando = stdIn.readLine()).equals("6")) {

                // Envia o comando ao servidor
                out.println(comando);
                out.flush();

                //recebo do servidor e leio
                /*switch (resposta = in.readLine()) {
                    case "LOGIN": {
                        Menu.login();
                        break;
                    }
                    case "REGISTAR": {
                        Menu.registar();
                        break;
                    }
                    case "VERLEILOES": {
                        Menu.verLeiloes();
                        break;
                    }
                    case "VENDERITEM": {
                        Menu.venderItem();
                        break;
                    }
                    case "LICITARITEM": {
                        Menu.licitarItem();
                        break;
                    }
                    case "TERMINARLEILAO": {
                        Menu.terminarLeilao();
                        break;
                    }
                    case "SAIR": {
                        Menu.sair();
                        break;
                    }
                    default: {
                        System.out.println(resposta);
                    }

                }*/
                resposta = in.readLine();
                //ALTEREI A PARTIR DAQUI
                switch (resposta) {
                    
                    case "DECORRER": {
                        while (!("###".equals(resposta))) {
                            System.out.println(resposta);
                            resposta = in.readLine();
                            //System.out.println("estou no switch");
                        }
                        //resposta = in.readLine();
                        break;
                    }
                    default: {
                        //System.out.println("estou no default");
                        System.out.println(resposta);
                        System.out.print("> ");
                        
                        //break;
                    }
                }
                //System.out.println("saí do switch ");
                //ATÉ AQUI
                //System.out.println(resposta);
                //System.out.print("> ");

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


    /*
    public static void mostra_menu() {
        System.out.println("###############escolha uma opcão:##########################");
        
    }*/
}
