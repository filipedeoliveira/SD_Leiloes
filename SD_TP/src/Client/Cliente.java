/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Filipe Oliveira
 */
public class Cliente {

    private static Socket cliente;
    private static PrintWriter output;
    private static BufferedReader input;
    private static BufferedReader stdInput;

    public static void main(String[] args) throws Exception {

        try {
            String comando, respota;
            cliente = new Socket("localhost", 2048);
            output = new PrintWriter(cliente.getOutputStream());
            input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            stdInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("##############");
            System.out.print("> ");

            MenuCliente.mostra_menu();

            //Lê opções
            while (!(comando = stdInput.readLine()).equals("6")) {

                //Envia ao servidor
                output.println(comando);
                output.flush();

                //Recebe e lê resposta do servidor
                switch (respota = input.readLine()) {
                    case "0": {
                        MenuCliente.login();
                        break;
                    }
                    case "1": {
                        MenuCliente.registar();
                        break;
                    }
                    case "2": {
                        MenuCliente.verLeiloes();
                        break;
                    }
                    case "3": {
                        MenuCliente.verItem();
                        break;
                    }
                    case "4": {
                        MenuCliente.licitaItem();
                        break;
                    }
                    case "5": {
                        MenuCliente.terminaLeilao();
                        break;
                    }
                    default: {
                        //Resposta anteriror
                        System.out.println(respota);
                    }
                }

                System.out.print(">> ");

            }
            //Enviar comando exit (6) ao servidor e faz logout
            output.println(comando);
            output.flush();

            cliente.shutdownInput();
            cliente.shutdownOutput();
            cliente.close();

        } catch (Exception e) {
            System.out.println("Err:_Servidor Desligado!");
        }

    }

}
