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

            mostra_menu1();

            System.out.print("> ");
            // Lê um comando do teclado
            
            while (!(comando = stdIn.readLine()).equals("6")) {

                // Envia o comando ao servidor
                out.println(comando);
                out.flush();

                //recebo do servidor
                resposta = in.readLine();
                System.out.println(resposta);
                switch (resposta) {
                    case "7": {//aqui é para mostrar o menu, sempre que no servidor chego ao fim do médudo. 
                        mostra_menu();
                        break;
                    }

                    default: {
                        System.out.println(resposta);
                        break;

                    }

                }
                System.out.print("> ");

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

    public static void mostra_menu1() {
        System.out.println("###############escolha uma opcão:##########################");
        System.out.println("0-Login");
        System.out.println("1-Registar");
    }

    public static void mostra_menu() {
        System.out.println("###############escolha uma opcão:##########################");
        System.out.println("2-Ver Leilões em curso");
        System.out.println("3-Vender Item");
        System.out.println("4-Licitar Item");
        System.out.println("5-Terminar Leilão");
        System.out.println("6-Sair");
    }
}
