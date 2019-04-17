package server;

import helma.xmlrpc.WebServer;
import java.util.Hashtable;

// Description : Get message from client, and display it on the screen
public class Server {

    public static void main(String[] args) {
        try {
            System.out.println("Start XML-RPC Server...");
            WebServer webServer = new WebServer(8081);
            webServer.addHandler("myServer", new Server());
            webServer.start();

            System.out.println("Started successfully.");
        } catch (Exception exception) {
            System.err.println("Server : " + exception);
        }
    }

    // Method to get Message (String) from client
    public void pesanClient(String msg) {
        System.out.println(msg);
    }
    public int soma (int num1, int num2){
        return num1+num2;
    }
    
        // Method to get data (struct) from client
    public void enviaStruct(Hashtable pessoa) {
        int cpf = (Integer) pessoa.get("cpf");
        String nome = (String) pessoa.get("nome");
        String setor = (String) pessoa.get("setor");
        System.out.println("Data from client : "+ cpf + " - " + nome + " - " + setor);
//        System.out.println("Data from client : "+ cpf + " - " + nome);
    }
}
