package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
        try {
            while (true) {
                //cliente cria o seu socket e se conecta ao servidor
                Socket cliente = new Socket("localhost", 1234);

                //stream para envio de dados para o servidor
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                //stream para o recebimento de dados do servidor
                DataInputStream in = new DataInputStream(cliente.getInputStream());

                //cliente envia uma string para o servidor
                System.out.println("Digite uma mensagem: ");
                Scanner teclado = new Scanner(System.in);
                String mensagem = teclado.nextLine();
                out.writeUTF(mensagem);

                //cliente espera uma resposta do servidor
                String resposta = in.readUTF();//bloqueante

                System.out.println("Resposta do servidor: " + resposta);
            }
        } catch (IOException ex) {
            System.out.println("Servidor não encontrado");
        }

    }
}
