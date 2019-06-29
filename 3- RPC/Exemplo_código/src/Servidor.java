

import helma.xmlrpc.WebServer;
import java.util.Hashtable;

public class Servidor {

    public static void main(String[] args) {
        try {
            System.out.println("Start XML-RPC Server...");
            WebServer webServer = new WebServer(8081);
            webServer.addHandler("myServer", new Servidor());
            webServer.start();

            System.out.println("Servidor iniciado!");
        } catch (Exception exception) {
            System.err.println("Server : " + exception);
        }
    }

    public String msgUpperCase(String msg) {
        System.out.println(msg);
        return msg.toUpperCase();
    }

    public int soma(int num1, int num2) {
        System.out.println("Resultado:"+ (num1+num2));
        return num1 + num2;
    }

    public boolean enviaDados(Hashtable pessoa) {
        int cpf = (Integer) pessoa.get("cpf");
        String nome = (String) pessoa.get("nome");
        System.out.println("Pessoa:  " + nome + " - CPF: " + nome);
        
        return true;
    }
}
