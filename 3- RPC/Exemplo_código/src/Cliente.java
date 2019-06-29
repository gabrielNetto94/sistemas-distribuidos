
import helma.xmlrpc.XmlRpcClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class Cliente {

    public static void main(String[] args) {
        try {
            // connect server
            XmlRpcClient server = new XmlRpcClient("http://localhost:8081/RPCclient");
            Vector parametros = new Vector();
            Vector numeros = new Vector();
            Vector paramPessoa = new Vector();

            Scanner scan = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

//EXEMPLO 1

                System.out.print("Digite mensagem: ");
                String msg = scan.nextLine();
                parametros.addElement(msg);
                Object resultado = server.execute("myServer.msgUpperCase", parametros);
                System.out.println("Resposta do Servidor: " + resultado +"\n");
                parametros.clear();
                
//EXEMPLO 2

                System.out.println("Digite 1º número: ");
                int n1 = scan.nextInt();
                numeros.add(n1);
                System.out.println("Digite 2º número: ");
                int n2 = scan.nextInt();
                numeros.add(n2);

                Object resposta = server.execute("myServer.soma", numeros);
                System.out.println("Resultado da soma de " + n1 + " + " + n2 + " = " + resposta+"");
                numeros.clear();
                
                 
//EXEMPLO 3
                Hashtable pessoa = new Hashtable();
                
                scan.nextLine();
                System.out.println("Digite nome");
                String nome = scan.nextLine();
                System.out.println("Digite CPF");
                int cpf = scan.nextInt();
                
                pessoa.put("cpf", cpf);
                pessoa.put("nome", nome);
                
                paramPessoa.addElement(pessoa);

                Object result = server.execute("myServer.enviaDados", paramPessoa);
                if (result.equals(true)) {
                    System.out.println("Dados enviados com sucesso!\n");
                } else {

                }
                paramPessoa.clear();
                
                System.out.println("Pressione ENTER para continuar");
                System.out.println("-----------------------------------------------------------------------------------");
                scan.nextLine();
                scan.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
