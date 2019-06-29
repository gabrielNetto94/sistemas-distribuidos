package Exercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {
            //servidor cria um socket servidor na porta 1234
            ServerSocket servidor = new ServerSocket(1234);
            System.out.println("server created on the port: 1234");

            //servidor aguarda uma conexão de algum cliente
            Socket cliente = servidor.accept();//bloqueante
            System.out.println("Client Recevied");

            //stream para envio de dados para o cliente
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            //stream para o recebimento de dados do cliente
            final DataInputStream in = new DataInputStream(cliente.getInputStream());

            //teclado
            Scanner teclado = new Scanner(System.in);

            while (true) {
                //recebimento de mensagens do cliente
                try {
                    String number1;
                    String operation;
                    String number2;
                    float result;

                    number1 = in.readUTF();
                    operation = in.readUTF();
                    number2 = in.readUTF();

                    System.out.println("I received number 1: " + number1);
                    System.out.println("I received the operation: " + operation);
                    System.out.println("I received number 2: " + number2);
                    
                    if (operation.equals("+")) {
                        result = (Integer.parseInt(number1) + Integer.parseInt(number2));
                        out.writeFloat(result);
                    }
                    if (operation.equals("-")) {
                        result = (Integer.parseInt(number1) - Integer.parseInt(number2));
                        out.writeFloat(result);
                    }
                    
                    if (operation.equals("*")) {
                        result = (Integer.parseInt(number1) * Integer.parseInt(number2));
                        out.writeFloat(result);
                    }
                    
                    if (operation.equals("/")) {
                        result = (Integer.parseInt(number1) / Integer.parseInt(number2));
                        out.writeFloat(result);
                    }

                } catch (IOException ex) {
                    System.out.println("Problem in receiving client messages");
                    break;
                }

            }
        } catch (IOException ex) {
            System.out.println("Erro na criação do servidor");
            ex.printStackTrace();
        }
    }
}
