
package servidor;

import Dados.Clientes;
import Dados.Licitacoes;
import Dados.Vendas;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    private static Clientes clientes;
    private static Vendas vendas;
    private static Licitacoes licitacoes;
    
    public static void main(String[] args) {     
        try {            
            ServerSocket servidor = new ServerSocket(2048);
            clientes = new Clientes();           
            vendas = new Vendas();
            licitacoes = new Licitacoes();
            
            while (true){  
            System.out.println("Server on!");
                Socket cliente_user = servidor.accept();
                ServidorRunnable clientHandler = new ServidorRunnable(cliente_user, clientes, vendas, licitacoes);
                Thread t = new Thread (clientHandler);
                t.start();
            }      
            
        } catch (IOException ex) {
            System.out.println("Conexao nao permitida!");
        }
        
    }
    
}

