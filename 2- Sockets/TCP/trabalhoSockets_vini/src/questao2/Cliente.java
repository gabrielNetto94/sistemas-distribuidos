package questao2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        while (true) {

            try {
                //cliente cria o seu socket e se conecta ao servidor
                Socket cliente = new Socket("localhost", 1234);
                Scanner entrada = new Scanner(System.in);

                //stream para envio de dados para o servidor
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                //stream para o recebimento de dados do servidor
                DataInputStream in = new DataInputStream(cliente.getInputStream());

                //cliente envia uma string para o servidor
                System.out.println("Valor 1:");
                String valor1 = entrada.nextLine();
                out.writeUTF(valor1);

                System.out.println("Operação:");
                String opera = entrada.nextLine();
                out.writeUTF(opera);

                System.out.println("Valor2:");
                String valor2 = entrada.nextLine();
                out.writeUTF(valor2);

                //cliente espera uma resposta do servidor
                String resposta = in.readUTF();//bloqueante
                System.out.println("Resposta do servidor: " + resposta);

            } catch (IOException ex) {
                System.out.println("Servidor não encontrado");
            }
        }
    }
}
