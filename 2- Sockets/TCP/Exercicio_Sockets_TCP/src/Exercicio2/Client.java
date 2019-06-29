package Exercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            //cliente cria o seu socket e se conecta ao servidor
            Socket cliente = new Socket("localhost", 1234);

            //stream para envio de dados para o servidor
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            //stream para o recebimento de dados do servidor
            final DataInputStream in = new DataInputStream(cliente.getInputStream());

            //teclado
            Scanner key = new Scanner(System.in);

            while (true) {

                System.out.println("Value 1:");
                String value1 = key.nextLine();
                out.writeUTF(value1);

                System.out.println("Operation:");
                String opera = key.nextLine();
                out.writeUTF(opera);

                System.out.println("Value 2:");
                String value2 = key.nextLine();
                out.writeUTF(value2);

                //cliente espera uma resposta do servidor
                float resposta = in.readFloat();//bloqueante
                System.out.println("Resposta do servidor: " + resposta);

            }
        }catch (IOException ex) {
            System.out.println("Server not found!");
        }

        
    }
}
