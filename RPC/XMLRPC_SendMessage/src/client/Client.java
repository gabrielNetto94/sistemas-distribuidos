package client;

import helma.xmlrpc.XmlRpcClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class Client {

    public static void main(String[] args) {
        try {
            // connect server
            XmlRpcClient server = new XmlRpcClient("http://localhost:8081/RPCclient");
            Vector params = new Vector();
            Vector numeros = new Vector();

            Scanner scan = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                //chamando método void  
//                System.out.print("Message to Server : ");
//                String pesan = scan.nextLine();
//                params.addElement(pesan);
//                Object result = server.execute("myServer.pesanClient", params);

                //chamando método com 2 parâmetros int
//                System.out.println("Digite 1º número: ");
//                int n1 = scan.nextInt();
//                numeros.add(n1);
//                System.out.println("Digite 2º número: ");
//                int n2 = scan.nextInt();
//                numeros.add(n2);
//                //chamada do método no servidor
//                Object resposta = server.execute("myServer.soma", numeros);
//                System.out.println("Resultado da soma de " + n1 + " + " + n2 + " = " + resposta);
                // create struct
//                System.out.println("Digite cpf: ");
//                int cpf = in.read();
//                System.out.println("Digite nome: ");
//                String nome = in.readLine();
//                System.out.println("Digite setor: ");
//                String setor = in.readLine();
//                System.out.println(cpf+" "+nome);
                
                Hashtable pessoa = new Hashtable();
                int cpf = 123267;
                String nome = "Gabriel Neto";
                String setor = "Suporte de TI";

                pessoa.put("cpf", cpf);
                pessoa.put("nome", nome);
                pessoa.put("setor", setor);

                params.addElement(pessoa);
                // send struct to server
                Object result = server.execute("myServer.enviaStruct", params);

//                params.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
