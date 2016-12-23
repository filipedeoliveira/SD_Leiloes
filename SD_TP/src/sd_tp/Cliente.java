/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_tp;

/**
 *
 * @author Filipe Oliveira
 */
public class Cliente {

    private int idCliente;
    private String userName;
    private String password;
    private int tipo; //0 - Comprador 1 - Vendedor

    public Cliente() {
        this.idCliente = 0;
        this.userName = "";
        this.password = "";
        this.tipo = 0;

    }

    public Cliente(int idCliente, String userName, String password, int tipo) {
        this.idCliente = idCliente;
        this.userName = userName;
        this.password = password;
        this.tipo = tipo;
    }

    public Cliente(Cliente cl) {
        this.idCliente = cl.getIdCliente();
        this.userName = cl.getUserName();
        this.password = cl.getPassword();
        this.tipo = cl.getTipo();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", userName=" + userName + ", password=" + password + ", tipo=" + tipo + '}';
    }
}
