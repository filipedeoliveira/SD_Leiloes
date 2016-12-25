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
        escolha(request, az);

    }

    public void escolha(int i, Armazenamento az) throws myException {

        switch (i) {
            case 0:
                Login(az);
                break;
            case 1:
                Registar(az);
                break;
            case 2:
                Ver_Leiloes(az);
                break;
            case 3:
                Vender_Item(az);
                break;
            case 4:
                Licitar_Item(az);
                break;
            case 5:
                Terminar_Leilao(az);
                break;
            case 6:
                Sair(az);
                break;
            default:
                throw new myException("Tente Novamente");

        }
    }

    private void Login(Armazenamento az) throws myException {

        String serverResp = "";

        mostraPrint("Digite o seu Cliente: ");
        String nome = Input.lerString();
        mostraPrint("Digite password ");
        String pass = Input.lerString();

        output.println(0 + "," + nome + "," + pass);
        try {
            serverResp = input.readLine();//falta tratar a resposta
            menuCliente(az);
        } catch (IOException e) {
            throw new myException("Sem resposta do servidor!");
        }
    }

    private void Registar(Armazenamento az) throws myException {
        String serverResp = "";

        mostraPrint("Digite um username: ");
        String nome = Input.lerString();
        mostraPrint("Digite uma password ");
        String pass = Input.lerString();

        output.println(1 + "," + nome + "," + pass);
        try {
            serverResp = input.readLine();//falta tratar a resposta

        } catch (IOException e) {
            throw new myException("Sem resposta do servidor!");
        }
    }

    //Aqui não sei bem, pois é para receber um hasmap
    private void Ver_Leiloes(Armazenamento az) throws myException {
        mostraPrint("leilões a decorrer");
        String serverResp = "";
        try {
            serverResp = input.readLine();//falta tratar a resposta
        } catch (IOException e) {
            throw new myException("Sem resposta do servidor!");
        }

    }

    private void Vender_Item(Armazenamento az) throws myException {

    }

    private void Licitar_Item(Armazenamento az) throws myException {

    }

    private void Terminar_Leilao(Armazenamento az) throws myException {

    }

    private void Sair(Armazenamento az) throws myException {

    }

}
