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
import exceptions.NaoExisteItensException;
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
import java.util.TreeMap;
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
    public TreeMap<String, String> mensagens;

    ServidorRunnable(Socket cliente_a_usar, Clientes clientes, Vendas vendas, Licitacoes licitacoes) throws IOException {
        this.nome = null;
        this.cliente = cliente_a_usar;
        this.utilizador = null;
        this.clientes = clientes;
        this.vendas = vendas;
        this.licitacoes = licitacoes;
        this.outputServidor = new PrintWriter(cliente.getOutputStream(), true);
        mensagens = new TreeMap<String, String>();
        this.inputCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String mensagem;
            this.outputServidor = new PrintWriter(cliente.getOutputStream(), true);
            this.inputCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            this.BD();
            while ((mensagem = inputCliente.readLine()) != null) {
                System.out.println("[" + cliente.getPort() + "] " + mensagem);
                this.escolha(mensagem);
            }
        } catch (IOException ex) { // Caso o cliente se desligue espontaneamente, com sessao ativa, efetua-se o logout
            System.out.println(cliente.getPort() + " desligou-se!");

            if (this.nome != null) {
                try {
                    clientes.logOut(this.nome);
                } catch (UtilizadorNaoExisteException | UtilizadorOfflineException ex1) {
                }
            }
        } catch (UtilizadorJaExisteException ex) {
            Logger.getLogger(ServidorRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            outputServidor.close();
        }
    }

    public void mostra_menu() {
        outputServidor.println("Escolha uma opção: {0,1,2,3,4,5,6,7}");
    }

    public void escolha(String msg) throws IOException, UtilizadorJaExisteException {
        // i = Integer.parseInt(s);
        String[] args = msg.split("\\s+");
        String comando = args[0].toLowerCase();
        int nArgs = args.length;
        int size = msg.length();

        switch (comando) {
            case "0": {
                if (this.nome != null) {
                    outputServidor.println("Já se encontra ligado!");
                    outputServidor.flush();
                    break;
                }
                if (nArgs < 3) {
                    outputServidor.println("Numeros de argumentos errados! ex: <C> <nome> <passe> ");
                    outputServidor.flush();
                    break;
                }
                try {
                    this.utilizador = clientes.logIn(args[1], args[2]);
                    this.nome = args[1];
                    outputServidor.println("Login efetuado com sucesso");
                    outputServidor.flush();
                } catch (UtilizadorNaoExisteException ex) {
                    outputServidor.println("O utilizador: " + args[1] + " não exite!");
                    outputServidor.flush();
                } catch (UtilizadorOnlineException ex) {
                    outputServidor.println("O utilizado: " + args[1] + " já está online!");
                    outputServidor.flush();
                } catch (PasswordIncorretaException ex) {
                    outputServidor.println("A password inserida é incorreta! ");
                    outputServidor.flush();
                }
                break;
            }

            case "1": {
                if (nArgs != 3 && nArgs != 2) {
                    outputServidor.println("Numeros de argumentos errados! ex: <C> <nome> <passe>");
                    outputServidor.flush();
                    break;
                }
                try {
                    if (nArgs == 3) {
                        clientes.adicionarCliente(args[1], args[2]);
                        outputServidor.println("Utilizador registado! ");
                        outputServidor.flush();
                    }

                } catch (UtilizadorJaExisteException ex) {
                    outputServidor.println("O utilizador " + args[1] + " já existe!");
                    outputServidor.flush();
                }
                break;
            }
            case "2": {

                if (nArgs != 2) {
                    outputServidor.println("Numero de argumentos invalido! <C> <Ver>");
                    outputServidor.flush();
                    break;
                }
                if (this.nome == null) {
                    outputServidor.println("Nao se encontra online!");
                    outputServidor.flush();
                    break;
                }

                int tam = vendas.size();
                if (tam == 0) {
                    outputServidor.println("Não existe leilões activos!");
                    outputServidor.flush();
                    break;
                }
                outputServidor.println("DECORRER");

                for (int i = 0; i < vendas.size(); i++) {
                    String dono = "";
                    String est = "0";
                    Venda it = vendas.get(i);
                    String result = maiorLicitacao2(i, this.nome);
                    int id = it.getId();
                    String produto = it.getNome_produto();
                    String descricao = it.getDescricao();
                    String myCliente = it.getCliente();
                    if (myCliente.equals(this.nome)) {
                        dono = "*";
                    }
                    int estado = it.getEstado();
                    if (estado == 1) {
                        est = "terminado";
                    }
                    outputServidor.println("Item{ id= " + id + "nome_produto= " + produto + ", descricao= " + descricao + ", Cliente= " + myCliente + ", estado= " + est + " " + dono + " " + result + '}');
                }
                outputServidor.println("###");
                break;
            }
            case "3": {
                if (nArgs != 3) {
                    outputServidor.println("Numero de argumentos invalido! <C> <NomeProduto> <Descricao>");
                    outputServidor.flush();
                    break;
                }
                if (this.nome == null) {
                    outputServidor.println("Nao se encontra online!");
                    outputServidor.flush();
                    break;
                }
                Venda item = new Venda(this.inc, args[1], args[2], this.nome, 0);
                vendas.put(this.inc, item);
                outputServidor.println("Id da venda" + inc);
                inc++;
                break;
            }
            case "4": {
                if (this.nome == null) {
                    outputServidor.println("Nao se encontra online!");
                    outputServidor.flush();
                    break;
                }
                if (nArgs != 2) {
                    outputServidor.println("Numero de argumentos invalido! <4> <Licitar> ");
                    outputServidor.flush();
                    break;
                }
                Licitar_Item();
                break;
            }
            case "5": {
                if (this.nome == null) {
                    outputServidor.println("Nao se encontra online!");
                    outputServidor.flush();
                    break;
                }
                if (nArgs != 2) {
                    outputServidor.println("Numero de argumentos invalido! <5> <Terminar>");
                    outputServidor.flush();
                    break;
                }
                Terminar_Leilao();
                break;
            }
            case "6": {

                if (nArgs != 2) {
                    outputServidor.println("Numero de argumentos invalido! Ex:  <6> <Sair>");
                    outputServidor.flush();
                    break;
                }
                if (this.nome == null) {
                    outputServidor.println("Nao se encontra online, não estava autenticado!");
                    outputServidor.flush();
                }
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

                break;
            }
            case "7": {
                if (this.nome == null) {
                    outputServidor.println("Nao se encontra online!");
                    outputServidor.flush();
                    break;
                }
                if (nArgs != 2) {
                    outputServidor.println("Numero de argumentos invalido! <7> <Mensagem>");
                    outputServidor.flush();
                    break;
                }
                verMensagens();
                break;
            }
            default: {
                outputServidor.println("Novo Comando!");
                outputServidor.flush();
                break;
            }

        }
    }

    public void Licitar_Item() throws IOException {

        outputServidor.println("DECORRER");

        for (int i = 0; i < vendas.size(); i++) {
            String dono = "";
            String est = "0";
            Venda it = vendas.get(i);
            String result = maiorLicitacao2(i, this.nome);
            int id = it.getId();
            String produto = it.getNome_produto();
            String descricao = it.getDescricao();
            String cliente = it.getCliente();
            if (cliente.equals(this.nome)) {
                dono = "*";
            }
            int estado = it.getEstado();
            if (estado == 1) {
                est = "terminado";
            }
            outputServidor.println("Item{ id = " + id + " nome_produto= " + produto + ", descricao= " + descricao + ", Cliente= " + cliente + ", estado= " + est + " " + dono + " " + result + '}');
        }
        outputServidor.println("###");
        String lixo = inputCliente.readLine();
        outputServidor.println("escolha o id de um Item");
        String id = inputCliente.readLine();
        int request = Integer.parseInt(id);
        if (vendas.containsKey(request) && (vendas.get(request).getEstado() != 1) && (!vendas.get(request).getCliente().equals(this.nome))) {
            outputServidor.println("Digite valor que deseja ofercer ");
            String value = inputCliente.readLine();
            int money = Integer.parseInt(value);
            licitacoes.adicionarLicitacao(request, nome, money);
            outputServidor.println("Licitacao efetuada");
            //mostra_menu();
        } else {
            outputServidor.println("Item invalido!");
            outputServidor.flush();
            //mostra_menu();
        }
    }

    public void Terminar_Leilao() throws IOException {
        outputServidor.println("ATIVOS");
        int size = vendas.size();
        for (int i = 0; i < size; i++) {
            Venda it = vendas.get(i);
            if (it.getCliente().equals(nome) && (it.getEstado() != 1)) {
                int id = it.getId();
                String produto = it.getNome_produto();
                String descricao = it.getDescricao();
                String cliente = it.getCliente();
                int estado = it.getEstado();
                outputServidor.println("Item{ id= " + id + " nome_produto= " + produto + ", descricao= " + descricao + ", Cliente= " + cliente + ", estado= " + estado + '}');
            }
        }
        outputServidor.println("###");
        String lixo = inputCliente.readLine();
        outputServidor.println("Digite o leilão que quer terminar ");
        String id = inputCliente.readLine();
        int value = Integer.parseInt(id);
        vendas.get(value).setEstado(1);
        maiorLicitacao(value);
        outputServidor.println("Leilao terminou");

    }

    public String maiorLicitacao2(int id, String name) {
        String result = "";
        if (licitacoes.containsKey(id)) {
            ArrayList<Licitacao> lis = licitacoes.get(id);
            float maior = 0;
            String vencedor = "";
            for (int j = 0; j < lis.size(); j++) {
                if (lis.get(j).getValor() > maior) {
                    maior = lis.get(j).getValor();
                    vencedor = lis.get(j).getCliente();
                }
            }
            if (vencedor.equals(name)) {
                result = "+";
            }

        }

        return result;
    }

    public void maiorLicitacao(int id) {
        ArrayList<Licitacao> lis = licitacoes.get(id);
        float maior = 0;
        String vencedor = "";
        for (int j = 0; j < lis.size(); j++) {
            if (lis.get(j).getValor() > maior) {
                maior = lis.get(j).getValor();
                vencedor = lis.get(j).getCliente();
            }
        }
        if (!mensagens.containsKey(this.nome)) {
            String s = "Vendeu o item " + id + " por " + maior;
            mensagens.put(this.nome, s);
        } else {
            String s = "Vendeu o item " + id + " por " + maior;
            mensagens.put(this.nome, s);
        }
        if (!mensagens.containsKey(vencedor)) {
            String p = "Venceu o leilão pelo item " + id;
            mensagens.put(vencedor, p);
        } else {
            String p = "Venceu o leilão pelo item " + id;
            mensagens.put(vencedor, p);
        }
    }

    public void BD() throws UtilizadorJaExisteException {
        clientes.adicionarCliente("alex", "alex");
        clientes.adicionarCliente("filipe", "filipe");
        clientes.adicionarCliente("manu", "manu");
        clientes.adicionarCliente("nuno", "nuno");

        vendas.adicionarVenda(inc++, "TV", "TV 4K", "alex", 0);
        vendas.adicionarVenda(inc++, "PC", "Toshiba", "filipe", 0);
        vendas.adicionarVenda(inc++, "Relogio", "casio", "manu", 0);
        vendas.adicionarVenda(inc++, "Carro", "BMW", "nuno", 0);
        vendas.adicionarVenda(inc++, "Bola", "nike", "alex", 0);
        vendas.adicionarVenda(inc++, "DVD", "Star Wars", "filipe", 0);

        licitacoes.adicionarLicitacao(0, "nuno", 300);
        licitacoes.adicionarLicitacao(0, "filipe", 200);
        licitacoes.adicionarLicitacao(2, "manu", 20);
        licitacoes.adicionarLicitacao(3, "alex", 7000);
        licitacoes.adicionarLicitacao(3, "manu", 8000);
        licitacoes.adicionarLicitacao(1, "alex", 700);
        licitacoes.adicionarLicitacao(4, "filipe", 30);

    }

    public void verMensagens() {
        outputServidor.println("MENSAGENS");
        String mens = mensagens.get(this.nome);
        outputServidor.println(">>> " + mens);
        outputServidor.println("###");
    }

}
