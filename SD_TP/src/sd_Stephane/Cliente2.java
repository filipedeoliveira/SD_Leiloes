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
import static sd_Stephane.Menu.escolha;
import static sd_Stephane.Menu.mostra_menu;
import sd_Stephane.myException;

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

    public void mostraPrint(String msg) {
        System.out.println(msg);
    }

    public void menuCliente(Armazenamento az) throws myException {
        mostraPrint("###############escolha uma opcão:##########################");
        mostraPrint("0-Login");
        mostraPrint("1-Registar");
        mostraPrint("2-Ver Leilões em curso");
        mostraPrint("3-Vender Item");
        mostraPrint("4-Licitar Item");
        mostraPrint("5-Terminar Leilão");
        mostraPrint("6-Sair");

        int request = Input.lerInt();
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
        escolha(request);

    }

    public void escolha(int i) throws myException {

        switch (i) {
            case 0:
                Login();
                break;
            case 1:
                Registar();
                break;
            case 2:
                Ver_Leiloes();
                break;
            case 3:
                Vender_Item();
                break;
            case 4:
                Licitar_Item();
                break;
            case 5:
                Terminar_Leilao();
                break;
            case 6:
                Sair();
                break;
            default:
                throw new myException("Tente Novamente");

        }
    }

    private void Login() throws myException {

        String serverResp = "";

        mostraPrint("Digite username do Cliente: ");
        String nome = Input.lerString();
        mostraPrint("Digite password ");
        String pass = Input.lerString();

        output.println(0 + "," + nome + "," + pass);
        try {
            serverResp = input.readLine();//falta tratar a resposta
        } catch (IOException e) {
            throw new myException("Sem resposta do servidor!");
        }
    }

    private void Registar() throws myException {

    }

    private void Ver_Leiloes() throws myException {

    }

    private void Vender_Item() throws myException {

    }

    private void Licitar_Item() throws myException {

    }

    private void Terminar_Leilao() throws myException {

    }

    private void Sair() throws myException {

    }

}
