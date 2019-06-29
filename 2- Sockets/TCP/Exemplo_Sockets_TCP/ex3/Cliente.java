package ex3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            //cliente cria o seu socket e se conecta ao servidor
            Socket cliente = new Socket("localhost", 1234);
            
            //stream para envio de dados para o servidor
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            //stream para o recebimento de dados do servidor
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            
            //teclado
            Scanner teclado = new Scanner(System.in);

            //criação de uma thread para recebimento de mensagens do servidor
            Thread t = new Thread() {
                public void run() {
                    while (true) {
                        //recebimento de mensagens do servidor
                        try {
                            String mensagemRecebida = in.readUTF();
                            System.out.println("Servidor enviou: " + mensagemRecebida);
                        } catch (IOException ex) {
                            System.out.println("Problema no recebimento de mensagens do servidor");
                            break;
                        }
                        
                    }
                }
            };
            t.start();
            
            //envio de mensagens para o servidor
            while (true) {
                String mensagemEnviar = teclado.nextLine();
                out.writeUTF(mensagemEnviar);
            }
        } catch (IOException ex) {
            System.out.println("Servidor não encontrado");
        }
        
    }
}
