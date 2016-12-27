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
        System.out.println("0-Login");
        System.out.println("1-Registar");
        System.out.println("2-Ver Leilões em curso");
        System.out.println("3-Vender Item");
        System.out.println("4-Licitar Item");
        System.out.println("5-Terminar Leilão");
        System.out.println("6-Sair");
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
