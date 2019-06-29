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

    boolean flag = true;

    public void iniciar() {
        //cria 5 processos que solicitarão ao coordenador permissão para executar
        Thread t1 = new Thread() {
            public void run() {
                int numThreads = 10;
                MyThread thread[] = new MyThread[numThreads];

                for (int i = 0; i < numThreads; i++) {
                    thread[i] = new MyThread();
                    thread[i].start();
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
                
                int var = 0;
                Thread t = new Thread() {
                    public void run() {
                        while (true) {
                            try {

                                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                                DataInputStream in = new DataInputStream(cliente.getInputStream());

                                String msg = in.readUTF();
                                if (msg.equals("permissao")) {
                                    if (!flag) {
                                        System.out.println("Permissao negada " + cliente.getPort());
                                        fila.add(cliente);
                                    }
                                    if (flag) {
                                        System.out.println("Permissao concedida " + cliente.getPort());
                                        out.writeBoolean(true);
                                        flag = false;
                                    }

                                }
                                if (msg.equals("libera")) {
                                    System.out.println("Thread " + cliente.getPort()+" liberou");
                                    if (!fila.isEmpty()) {
                                        flag = true;
                                        Socket c = fila.getFirst();
                                        System.out.println("Thread "+c.getPort()+" vai ser liberada");
                                        fila.removeFirst();
                                        DataOutputStream out2 = new DataOutputStream(c.getOutputStream());
                                        out.writeBoolean(true);
                                        flag = false;
                                        

                                    }
                                }
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

    public static void main(String[] args) {
        Principal p = new Principal();
        p.iniciar();
    }
}
