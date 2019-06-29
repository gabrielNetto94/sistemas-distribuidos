package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
            
            //servidor aguarda uma string do cliente
            String mensagem = in.readUTF();//bloqueante
            System.out.println("Recebi "+mensagem);
            
            //servidor monta a resposta toda em maiúscula
            String resposta = mensagem.toUpperCase();
            System.out.println("Vou responder "+resposta);
            
            //servidor envia uma mensagem de resposta para o cliente
            out.writeUTF(resposta);
            
        } catch (IOException ex) {
            System.out.println("Erro na criação do servidor");
            ex.printStackTrace();
        }
    }
}
