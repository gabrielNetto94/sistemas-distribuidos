package ex3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        try {
            //servidor cria um socket servidor na porta 1234
            ServerSocket servidor = new ServerSocket(1234);
            System.out.println("Servidor criado na porta 1234");

            //servidor aguarda uma conexão de algum cliente
            Socket cliente = servidor.accept();//bloqueante
            System.out.println("Cliente recebido");

            //stream para envio de dados para o cliente
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            //stream para o recebimento de dados do cliente
            DataInputStream in = new DataInputStream(cliente.getInputStream());

            //teclado
            Scanner teclado = new Scanner(System.in);

            //criação de uma thread para recebimento de mensagens do cliente
            Thread t = new Thread() {
                public void run() {
                    while (true) {
                        //recebimento de mensagens do cliente
                        try {
                            String mensagemRecebida = in.readUTF();
                            System.out.println("Cliente enviou: " + mensagemRecebida);
                        } catch (IOException ex) {
                            System.out.println("Problema no recebimento de mensagens do cliente");
                        }
                        
                    }
                }
            };
            t.start();
            
            //envio de mensagens para o cliente
            while (true) {
                String mensagemEnviar = teclado.nextLine();
                out.writeUTF(mensagemEnviar);
            }

        } catch (IOException ex) {
            System.out.println("Erro na criação do servidor");
            ex.printStackTrace();
        }
    }
}
