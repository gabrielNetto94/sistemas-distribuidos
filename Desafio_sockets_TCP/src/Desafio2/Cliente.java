/*
 2) Modifique o exercício 3 da Lista de exercícios de Sockets TCP (banco) de modo que: 
 a) O servidor lide com vários clientes e não somente 1, de modo a gerenciar mais de uma conta bancária; 
 b) um cliente possa criar uma conta ou se logar em uma já existente;
 */
package Desafio2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
             //Dados do cliente
            String nome;
            int cpf;
            int senha;
            int repeteSenha;
            int numConta;
            
            //envio de mensagens para o servidor
            while (true) {
                int op = 0;
                System.out.println("(1) Criar Conta (2) Acessar Conta");
                op = teclado.nextInt();

                if (op == 1) {
                    System.out.println("Digite Nome Completo");
                    nome = br.readLine();
                    System.out.println("Digite seu CPF:");
                    cpf = teclado.nextInt();
                    System.out.println("Digite uma senha para sua conta:");
                    senha = teclado.nextInt();
                    System.out.println("Repita a senha:");
                    repeteSenha = teclado.nextInt();

                    while (senha != repeteSenha) {
                        System.out.println("Senha não coincidem!");
                        System.out.println("Digite uma senha para sua conta:");
                       senha = teclado.nextInt();
                        System.out.println("Repita a senha:");
                        repeteSenha = teclado.nextInt();
                    }
                    System.out.println("Senha cadastrada com sucesso!");

                }
                if (op == 2) {
                    System.out.println("Menu: (1)sacar (2)depositar (3) ver saldo");
                    String mensagemEnviar = teclado.nextLine();

                    if (mensagemEnviar.equals("1")) {
                        //pede o valor a ser sacado
                        System.out.println("Qual o valor para sacar");
                        String valor = teclado.nextLine();
                        out.writeUTF(mensagemEnviar);
                        out.writeUTF(valor);

                    } else if (mensagemEnviar.equals("2")) {
                        System.out.println("Qual o valor para Depositar");
                        String valor = teclado.nextLine();

                        out.writeUTF(mensagemEnviar);
                        out.writeUTF(valor);
                    } else if (mensagemEnviar.equals("3")) {
                        out.writeUTF(mensagemEnviar);
                        out.writeUTF("");
                    } else {
                        out.writeUTF(mensagemEnviar);
                        out.writeUTF("");
                    }
                }

                String mensagemRecebida = in.readUTF();
                System.out.println("Servidor enviou: " + mensagemRecebida);
            }
        } catch (IOException ex) {
            System.out.println("Servidor não encontrado");
        }

    }

}
