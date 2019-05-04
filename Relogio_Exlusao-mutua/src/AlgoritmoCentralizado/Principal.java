package AlgoritmoCentralizado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    public Principal() {

    }
    public static boolean livre = true;

    public static void main(String[] args) {

        //cria 5 processos que solicitarão ao coordenador permissão para executar
        Thread t1 = new Thread() {
            public void run() {
                int numThreads = 5;
                MyThread thread[] = new MyThread[numThreads];
                
                for (int i = 0; i < numThreads; i++) {
                    thread[i] = new MyThread();
                    thread[i].start();
                    System.out.println("thread " + i + " criada");
                }
            }
        };
        t1.start();

        LinkedList<Socket> listaClientes = new LinkedList<>();
        LinkedList<Socket> fila = new LinkedList<>();

        try {
            //servidor cria um socket servidor na porta 1234
            ServerSocket servidor = new ServerSocket(1234);
            System.out.println("Servidor criado na porta 1234");

            while (true) {
                Socket cliente = servidor.accept();//bloqueante

                listaClientes.add(cliente);
                int var =0;
                Thread t = new Thread() {
                    public synchronized void run() {
                        while (true) {
                            try {

                                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                                DataInputStream in = new DataInputStream(cliente.getInputStream());
                                
                                
                                out.writeInt(teste(var));
                                
//                                String msg = in.readUTF();
//                                if (msg.equals("permissao")) {
//                                    if (!livre) {
//                                        System.out.println("permissao negada "+cliente.getPort());
//                                    }
//                                    if (livre) {
//                                        System.out.println("permissao concedida "+cliente.getPort());
//                                        out.writeBoolean(true);
//                                        livre = false;
//                                    }
//
//                                }
//                                if (msg.equals("libera")) {
//                                    if (!fila.isEmpty()) {
//                                        livre = true;
////                                        Socket c = fila.getFirst();
////                                        fila.removeFirst();
//
//                                    }
//
//                                }

                            } catch (IOException ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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

    synchronized public static int teste(int i){
        i++;
        return i;
    }
}
