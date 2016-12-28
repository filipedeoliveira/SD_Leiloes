/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Filipe Oliveira
 */
public class Menu {

    private static BufferedReader stdIn;
    private static PrintWriter out;

    public static void mostra_menu1() {
        System.out.println("###############escolha uma opcão:##########################");
        System.out.println("Com = {0, 1, 2, 3, 4, 5, 6}");
        System.out.println("Login - <0> <Nome> <Password>");
        System.out.println("Registar - <1> <Mome> <Password>");
        System.out.println("Ver Leilões em curso - <2> <ver>");
        System.out.println("Vender Item - <3> <NomeItem> <Descricao> ");
        System.out.println("Licitar Item - <4> ");
        System.out.println("Terminar Leilão - <5>");
        System.out.println("Sair <6> <sair> ou <6>");
        System.out.println("Mensagens <7> ");
        System.out.println("#########################################################");
        System.out.println("\n");
    }

    public static void menuInit() {
        System.out.println("############### Seja bem vindo! ##########################");
        System.out.println("Login - <0> <Nome> <Password>");
        System.out.println("Registar - <1> <Mome> <Password>");
        System.out.println("#########################################################");
        System.out.println("\n");
    }

    public static void login() throws IOException {
        Scanner s = new Scanner(System.in);
        String user, passe, mensagem;

        System.out.println("Menu Login ");
        System.out.println("Digite o seu username ");
        user = s.nextLine();
        System.out.println("Digite a sua password ");
        passe = s.nextLine();
        mensagem = passe + user;
        out.println(passe);

    }

    static void registar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void verLeiloes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void venderItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void licitarItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void terminarLeilao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void sair() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
