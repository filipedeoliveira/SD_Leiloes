/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Dados.Cliente;
import Dados.Clientes;
import Dados.Licitacoes;
import Dados.Venda;
import Dados.Licitacao;
import Dados.Vendas;
import exceptions.PasswordIncorretaException;
import exceptions.UtilizadorJaExisteException;
import exceptions.UtilizadorNaoExisteException;
import exceptions.UtilizadorOfflineException;
import exceptions.UtilizadorOnlineException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorRunnable implements Runnable {

    private String nome;//
    private Socket cliente;
    private Cliente utilizador;
    private PrintWriter outputServidor;
    private BufferedReader inputCliente;
    private Clientes clientes;//
    public Vendas vendas;
    private Licitacoes licitacoes;
    volatile static Integer inc = 0;

    ServidorRunnable(Socket cliente_a_usar, Clientes clientes, Vendas vendas, Licitacoes licitacoes) throws IOException {
        this.nome = null;
        this.cliente = cliente_a_usar;
        this.utilizador = null;
        this.clientes = clientes;
        this.vendas = vendas;
        this.licitacoes = licitacoes;
        this.outputServidor = new PrintWriter(cliente.getOutputStream(), true);
        this.inputCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String mensagem;
            this.outputServidor = new PrintWriter(cliente.getOutputStream(), true);
            this.inputCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            while ((mensagem = inputCliente.readLine()) != null) {
                System.out.println("[" + cliente.getPort() + "] " + mensagem);
                this.escolha(mensagem);
                mensagem = inputCliente.readLine();
            }
        } catch (IOException ex) { // Caso o cliente se desligue espontaneamente, com sessao ativa, efetua-se o logout
            System.out.println(cliente.getPort() + " desligou-se!");

            if (this.nome != null) {
                try {
                    clientes.logOut(this.nome);
                } catch (UtilizadorNaoExisteException | UtilizadorOfflineException ex1) {
                }
            }
        } finally {
            outputServidor.close();
        }
    }

    public void mostra_menu() {
        outputServidor.println("Escolha uma opção: {0,1,2,3,4,5,6}");
    }

    public void escolha(String s) throws IOException {
        int i = Integer.parseInt(s);
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

        }
    }

    public void Login() throws IOException {
       //outputServidor.println("LOGIN");

        if (this.nome != null) {
            outputServidor.println("Ja se encontra logado!");
            outputServidor.flush();
        }
        outputServidor.println("Digite username do Cliente: ");
        String nome = inputCliente.readLine();
        outputServidor.println("Digite password ");
        String pass = inputCliente.readLine();
        try {
            this.utilizador = clientes.logIn(nome, pass);
            this.nome = nome;
            outputServidor.println("Login efetuado com sucesso");
            outputServidor.flush();
        } catch (UtilizadorNaoExisteException ex) {
            outputServidor.println("O utilizador \"" + nome + "\" nao existe!");
            outputServidor.flush();
        } catch (UtilizadorOnlineException ex) {
            outputServidor.println("O utilizador \"" + nome + "\" ja esta logado!");
            outputServidor.flush();
        } catch (PasswordIncorretaException ex) {
            outputServidor.println("A password esta incorreta!");
            outputServidor.flush();
        }

        mostra_menu();
    }

    public void Registar() throws IOException {

        outputServidor.println("Registe se, digite o user name: ");
        //outputServidor.println("Digite username do Cliente: ");
        String nome = inputCliente.readLine();
        outputServidor.println("Digite password ");
        String pass = inputCliente.readLine();
        try {
            clientes.adicionarCliente(nome, pass);
            outputServidor.println("Utilizador adicionado");
            outputServidor.flush();
        } catch (UtilizadorJaExisteException ex) {
            outputServidor.println("O utilizador \"" + nome + "\" ja existe!");
            outputServidor.flush();
        }
        mostra_menu();
    }

    public void Ver_Leiloes() {
        outputServidor.println("DECORRER");
        for (int i = 0; i < vendas.size(); i++) {
            Venda it = vendas.get(i);
            int id = it.getId();
            String produto = it.getNome_produto();
            String descricao = it.getDescricao();
            String cliente = it.getCliente();
            int estado = it.getEstado();
            outputServidor.println("Item{ id= " + id + "nome_produto=" + produto + ", descricao=" + descricao + ", Cliente=" + cliente + ", estado=" + estado + '}');
        }
        outputServidor.println("###");
        mostra_menu();
    }

    public void Vender_Item() throws IOException {
        //outputServidor.println("Menu de vendas");
        outputServidor.println("Menu vendas - Digite nome do Item que deseja vender: ");
        String nome = inputCliente.readLine();
        outputServidor.println("Digite uma descrição ");
        String descricao = inputCliente.readLine();
        Venda item = new Venda(inc, nome, descricao, nome, 0);
        vendas.put(inc, item);
        outputServidor.println("Id da venda" + inc);
        inc++;
        mostra_menu();
    }

    public void Licitar_Item() throws IOException {

        System.out.println("leilões a decorrer");
        for (int i = 0; i < vendas.size(); i++) {
            Venda it = vendas.get(i);
            int id = it.getId();
            String produto = it.getNome_produto();
            String descricao = it.getDescricao();
            String cliente = it.getCliente();
            int estado = it.getEstado();
            outputServidor.println("Item{ id= " + id + "nome_produto=" + produto + ", descricao=" + descricao + ", Cliente=" + cliente + ", estado=" + estado + '}');
        }
        outputServidor.println("escolha o id de um Item");
        String id = inputCliente.readLine();
        int request = Integer.parseInt(id);
        if (vendas.containsKey(request)) {
            outputServidor.println("Digite valor que deseja ofercer ");
            String value = inputCliente.readLine();
            int money = Integer.parseInt(value);
            licitacoes.adicionarLicitacao(request, nome, money);
            mostra_menu();
        } else {
            outputServidor.println("Item invalido!");
            outputServidor.flush();
        }
    }

    public void Terminar_Leilao() throws IOException {
        outputServidor.println("Seus Leilões ativos");
        int size = vendas.size();
        for (int i = 0; i < size; i++) {
            Venda it = vendas.get(i);
            if (it.getCliente().equals(nome)) {
                int id = it.getId();
                String produto = it.getNome_produto();
                String descricao = it.getDescricao();
                String cliente = it.getCliente();
                int estado = it.getEstado();
                outputServidor.println("Item{ id=" + id + "nome_produto=" + produto + ", descricao=" + descricao + ", Cliente=" + cliente + ", estado=" + estado + '}');
            }
        }
        outputServidor.println("Digite o leilão que quer terminar ");
        String id = inputCliente.readLine();
        int value = Integer.parseInt(id);
        vendas.get(value).setEstado(1);
        ArrayList<Licitacao> lis = licitacoes.get(value);
        float maior = 0;
        String vencedor = "";
        for (int j = 0; j < lis.size(); j++) {
            if (lis.get(j).getValor() > maior) {
                maior = lis.get(j).getValor();
                vencedor = lis.get(j).getCliente();
            }
            outputServidor.println("Vencedor " + vencedor + " quantia " + maior + "€");
        }
        mostra_menu();
    }

    public void Sair() {

        if (this.nome == null) {
            outputServidor.println("Nao se encontra online!");
            outputServidor.flush();
        } else {
            try {
                clientes.logOut(this.nome);
                this.nome = null;
                this.utilizador = null;
                outputServidor.println("Logout efetuado com sucesso");
                outputServidor.flush();
            } catch (UtilizadorOfflineException ex) {
                outputServidor.println("Nao se encontra online!");
                outputServidor.flush();
            } catch (UtilizadorNaoExisteException ex) {
            }
        }
        mostra_menu();
    }

}
