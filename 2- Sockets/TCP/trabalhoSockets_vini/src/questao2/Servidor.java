package questao2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            //servidor cria um socket servidor na porta 1234
            ServerSocket servidor = new ServerSocket(1234);
            System.out.println("Servidor criado na porta 1234");
            
             while (true) {
                //servidor aguarda uma conexão de algum cliente
                Socket cliente = servidor.accept();//bloqueante
                System.out.println("Cliente recebido");

                //stream para envio de dados para o cliente
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                //stream para o recebimento de dados do cliente
                DataInputStream in = new DataInputStream(cliente.getInputStream());

                //servidor aguarda uma string do cliente
                String valor1 = in.readUTF();//bloqueante
                String operacao = in.readUTF();
                String valor2 = in.readUTF();
                
                System.out.println("Recebi " + valor1);
                System.out.println("Recebi " + valor2);
                System.out.println("Recebi " + operacao);
                float resposta = 0;
                switch (operacao){
                    case "+":
                        resposta = Integer.parseInt(valor1) + Integer.parseInt(valor2);
                        break;
                    case "-":
                        resposta = Integer.parseInt(valor1) - Integer.parseInt(valor2);
                        break;
                    case "*":
                        resposta = Integer.parseInt(valor1) * Integer.parseInt(valor2);
                        break;
                    case "/":
                        resposta = Integer.parseInt(valor1) / Integer.parseInt(valor2);
                        break;
                    default:
                        System.out.println("Operação invalida");
                            
                }
                out.writeUTF(String.valueOf(resposta));
            }
            
        } catch (IOException ex) {
            System.out.println("Erro na criação do servidor");
            ex.printStackTrace();
        }
    }
}