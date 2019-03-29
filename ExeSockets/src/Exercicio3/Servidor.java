package Exercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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

            float saldo = 0;
            float valor = 0;
            
            while (true) {
                int op = in.readInt();

                switch (op) {

                    case 1:
                        valor = in.readFloat();
                        if (valor > saldo) {
                            out.writeUTF("Operação de saque não efetuada! SALDO INVÁLIDO");
                        } else {
                            saldo -= valor;
                            out.writeUTF("Saque no valor de R$ " + String.valueOf(valor) + " efetuado!");
                        }
                        break;

                    case 2:
                        valor = in.readFloat();
                        saldo += valor;
                        out.writeUTF("Depósito no valor de R$ " + String.valueOf(valor) + " efetuado!");
                        valor = 0;
                        break;

                    case 3:
                        out.writeUTF("Saldo no valor de: R$ " + String.valueOf(saldo));
                        break;

                    default:
                        out.writeUTF("OPÇÃO INVÁLIDA!");
                        break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro na criação do servidor");
            ex.printStackTrace();
        }
    }

}
