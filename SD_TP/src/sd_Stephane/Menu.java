/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_Stephane;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Stéphane
 */
public class Menu {
    private static String Cliente_a_usar_o_sistema; 
    volatile static Integer inc=0;
    
    public static void mostra_menu(Armazenamento az){
        System.out.println("###############escolha uma opcão:##########################");
        System.out.println("0-Login");
        System.out.println("1-Registar");
        System.out.println("2-Ver Leilões em curso");
        System.out.println("3-Vender Item");
        System.out.println("4-Licitar Item");
        System.out.println("5-Terminar Leilão");
        System.out.println("6-Sair");
        //Scanner opcao = new Scanner(System.in);
        //int request = opcao.nextInt();
        int request = Input.lerInt();
        for(int i=0;i<4;i++){
        System.out.println();}
        escolha(request,az);
        
        
    }
    
    public static void escolha(int i, Armazenamento az){
    
        switch (i){
            case 0 : Login(az);
                break;
            case 1 : Registar(az);
                break;
            case 2 : Ver_Leiloes( az);
                break;
            case 3 : Vender_Item(az);
                break;
            case 4 : Licitar_Item( az);
                break;
            case 5 : Terminar_Leilao(az);
                break;    
            case 6 : Sair(az);
                break;    
        
        }
    }

    private static void Login(Armazenamento az) {
        System.out.println("Login");
        Scanner nomeCliente = new Scanner(System.in);
        System.out.println("Digite username do Cliente: ");
        String nome = nomeCliente.nextLine();
        System.out.println("Digite password ");
        String pass = nomeCliente.nextLine();
        Cliente cli = new Cliente(nome, pass);
        if (az.clientes.containsKey(nome)) {
            System.out.println("Login aceite");
            Cliente_a_usar_o_sistema= nome;
            for(int i=0;i<4;i++){
        System.out.println();}
            mostra_menu(az);
        } else {
            System.out.println("Login errado tente novamente");
            Login(az);
        }
    }

    private static void Registar(Armazenamento az) {
        System.out.println("Registe se");
        Scanner nomeCliente = new Scanner(System.in);
        System.out.println("Digite username do Cliente: ");
        String nome = nomeCliente.nextLine();
        System.out.println("Digite password ");
        String pass = nomeCliente.nextLine();
        Cliente_a_usar_o_sistema= nome;
        az.clientes.put(nome, pass);
        for(int i=0;i<4;i++){
        System.out.println();}
        mostra_menu(az);
    }

    private static void Ver_Leiloes(Armazenamento az) {
        System.out.println("leilões a decorrer");
        for (int i=0; i<az.items_pra_venda.size(); i++){
                Item it= az.items_pra_venda.get(i);
                String produto = it.getNome_produto();
                String descricao = it.getDescricao();
                String cliente = it.getCliente();
                int estado = it.getEstado();
                System.out.println("Item{" + "nome_produto=" + produto + ", descricao=" + descricao + ", Cliente=" + cliente + ", estado=" + estado + '}');
        }
        for(int i=0;i<4;i++){
        System.out.println();}
        mostra_menu(az);
    }

    private static void Vender_Item(Armazenamento az) {
        System.out.println("Menu de vendas");
        Scanner aux = new Scanner(System.in);
        System.out.println("Digite nome do Item que deseja vender: ");
        String nome = aux.nextLine();
        System.out.println("Digite uma descrição ");
        String descricao = aux.nextLine();
        Item item= new Item(nome,descricao,Cliente_a_usar_o_sistema,0);
        az.items_pra_venda.put(inc, item);
        System.out.println("Id da venda" + inc);
        inc++;
        for(int i=0;i<4;i++){
        System.out.println();}
         mostra_menu(az);
    }

    private static void Licitar_Item(Armazenamento az) {
        
        System.out.println("leilões a decorrer");
        for (int i=0; i<az.items_pra_venda.size(); i++){
                Item it= az.items_pra_venda.get(i);
                String produto = it.getNome_produto();
                String descricao = it.getDescricao();
                String cliente = it.getCliente();
                int estado = it.getEstado();
                System.out.println(i + " Item{" + "nome_produto=" + produto + ", descricao=" + descricao + ", Cliente=" + cliente + ", estado=" + estado + '}');
        }
        System.out.println("escolha o id de um Item");
        Scanner opcao = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        int request = opcao.nextInt();
        if(az.items_pra_venda.containsKey(request)){
            System.out.println("Digite valor que deseja ofercer ");
            Float value = aux.nextFloat();
            Licitacao li = new Licitacao(Cliente_a_usar_o_sistema,value);
            ArrayList<Licitacao> lis = az.licitacoes.get(request);
            lis.add(li);
            az.licitacoes.put(request, lis);
            for(int i=0;i<4;i++){
        System.out.println();}
            mostra_menu(az);
        }
        else {System.out.println("id errado escolha novamente");
        Licitar_Item(az);
        } 
       
    }

    private static void Terminar_Leilao(Armazenamento az) {
         System.out.println("Seus Leilões ativos");
         Scanner aux1 = new Scanner(System.in);
         int size=az.items_pra_venda.size();
         for (int i=0;i<size;i++){
             Item aux = az.items_pra_venda.get(i);
             if(aux.getCliente().equals(Cliente_a_usar_o_sistema)){
                 System.out.print(i); aux.toString();
             }
             }
        System.out.println("Digite o leilão que quer terminar ");
        int value = aux1.nextInt();
        az.items_pra_venda.get(value).setEstado(1);
        ArrayList<Licitacao> lis = az.licitacoes.get(value);
        float maior=0;
        String vencedor=""; 
        for (int j=0;j<lis.size();j++){
            if (lis.get(j).getValor()>maior){maior =lis.get(j).getValor();
            vencedor=lis.get(j).getCliente();
            }
        System.out.println("Vencedor " + vencedor + " quantia " + maior +"€");   
        }
        for(int i=0;i<4;i++){
        System.out.println();}
        mostra_menu(az);
    }

    private static void Sair(Armazenamento az) {
       Cliente_a_usar_o_sistema="";
       for(int i=0;i<4;i++){
        System.out.println();}
       mostra_menu(az);
    }
    
    
    public static void main(String[] args) {
    Cliente cli = new Cliente("Joao", "Joao");
    Cliente cli1 = new Cliente("pedro", "pedro");
    Armazenamento az = new Armazenamento();
    az.clientes.put("Joao","Joao");
     az.clientes.put("pedro", "pedro");
    Item it1 = new Item("TV","perfeitas condições","Joao",0);
    Item it2 = new Item("PC","bla bla","pedro",0);
    
    az.items_pra_venda.put(inc,it1); 
    inc++;
    az.items_pra_venda.put(inc,it2); 
    inc++;
    ArrayList<Licitacao> li = new ArrayList<Licitacao>();  
        li.add(new Licitacao("Joao", 75));
    az.licitacoes.put(0,li);
    
    mostra_menu(az);    
    
    }
    
    
}
