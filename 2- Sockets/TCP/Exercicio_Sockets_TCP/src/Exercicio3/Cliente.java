package Exercicio3;

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
            final DataInputStream in = new DataInputStream(cliente.getInputStream());

            Scanner teclado = new Scanner(System.in);

            while (true) {
                System.out.println("Digite (1) para Saque\tDigite (2) para Depósito\t(3) para Consultar Saldo");
                int opcao = teclado.nextInt();

                switch (opcao) {
                    case 1:
                        out.writeInt(1);
                        System.out.println("Digite valor para saque: ");
                        float valorSaque = teclado.nextFloat();
                        out.writeFloat(valorSaque);
                        break;

                    case 2:
                        out.writeInt(2);
                        System.out.println("Digite valor para depósito");
                        float valorDeposito = teclado.nextFloat();
                        out.writeFloat(valorDeposito);
                        break;
                        
                    case 3:    
                        out.writeInt(3);
                        break;
                    
                    default:
                        //manda um valor != para cair no default do switch case do servidor e receber o retorno "OPÇÃO INVÁLIDA!"
                        out.writeInt(4);
                        break;
                }

                String respostaServidor =  in.readUTF();
                System.out.println(respostaServidor);
            }
        } catch (IOException ex) {
            System.out.println("Servidor não encontrado");
        }

    }

}
