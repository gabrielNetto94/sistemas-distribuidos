package Cristian;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        try {
            //servidor cria um socket servidor na porta 1234
            ServerSocket servidor = new ServerSocket(1234);
            System.out.println("Servidor criado na porta 1234");

            while (true) {

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
                                boolean podeEnviar = false;
                                podeEnviar = in.readBoolean();

                                if (podeEnviar) {
                                    Random r = new Random();
                                    sleep(r.nextInt(100));
                                    long tempoServidor = System.currentTimeMillis();
                                    out.writeLong(tempoServidor);
                                }

                                System.out.println("Cliente na porta " + cliente.getPort());

                            } catch (IOException ex) {
                                System.out.println("Problema no recebimento de mensagens do cliente");
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                };
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Erro na criação do servidor");
            ex.printStackTrace();
        }
    }
}

