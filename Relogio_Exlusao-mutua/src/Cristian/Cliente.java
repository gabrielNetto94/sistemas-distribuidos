package Cristian;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) throws InterruptedException {
        try {
            //cliente cria o seu socket e se conecta ao servidor
            Socket cliente = new Socket("localhost", 1234);

            System.out.println(cliente.getLocalPort());
            
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            DataInputStream in = new DataInputStream(cliente.getInputStream());

            //teclado
            Scanner teclado = new Scanner(System.in);

            //RECEBE MENSAGEM
            Thread t = new Thread() {
                public void run() {
                    while (true) {
                        //recebimento de mensagens do servidor
                        try {
                            long tempoCliente = System.currentTimeMillis();
                            System.out.println("Cliente enviou no tempo: "+tempoCliente);
                            out.writeBoolean(true);
                            
                            long tempoServidor = in.readLong();
                            System.out.println("Tempo recebido do servidor " + tempoServidor);
                            System.out.println("Tempo de retorno do servidor: "+(tempoServidor-tempoCliente));
                            sleep(2000);

                        } catch (IOException ex) {
                            System.out.println("Problema no recebimento de mensagens do servidor");
                            break;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            };
            t.start();

        } catch (IOException ex) {
            System.out.println("Servidor não encontrado");
        }

    }
}
