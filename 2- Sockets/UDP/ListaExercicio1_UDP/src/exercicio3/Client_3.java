package exercicio3;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_3 {

    DatagramSocket socket;
    public String nomesProdutos[] = {"Tomate", "Arroz", "Feijão", "Carne", "Batata"};

    public Client_3() throws UnknownHostException, SocketException {

        this.socket = new DatagramSocket();
        recebeMensagem();

    }

    public void recebeMensagem() throws UnknownHostException, SocketException {

        Thread t = new Thread() {
            public void run() {

                while (true) {
                    try {

                        DatagramPacket pacoteRec = new DatagramPacket(new byte[100], 100);
                        socket.receive(pacoteRec);//bloqueante
                        byte bufferRec[] = pacoteRec.getData();
                        String msgRec = new String(bufferRec);
                        System.out.println(msgRec);

                    } catch (SocketException ex) {
                        System.out.println("Erro na criação do socket");
                    } catch (IOException ex) {
                        System.out.println("Erro no envio da mensagem");
                    }
                }
            }
        };
        t.start();
    }

    public class ThreadCliente extends Thread {

        Random random = new Random();
        int idCliente;

        public ThreadCliente(int idCliente) {
            this.idCliente = idCliente;

        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (i < 3) {
                    try {
                        //monta a mensagem com valores aleatórios
                        String msgEnviar = 
                                Integer.toString(idCliente)+':'+
                                nomesProdutos[random.nextInt(nomesProdutos.length)] + ':'+
                                Integer.toString(random.nextInt(10) + 1);//gera qtdVenda aleatório

                        byte bufferEnviar[] = msgEnviar.getBytes();
                        DatagramPacket pacoteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, InetAddress.getByName("localhost"), 1234);
                        socket.send(pacoteEnviar);

                        i++;
                        sleep(1000);

                    } catch (SocketException ex) {
                        System.out.println("Erro na criação do socket");
                    } catch (IOException ex) {
                        System.out.println("Erro no envio da mensagem");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client_3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                String msgFinal = "<" + idCliente + ">:LISTAGEM FINALIZADA";
//                System.out.println(msgFinal);
                byte bufferEnviar[] = msgFinal.getBytes();
                DatagramPacket pacoteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, InetAddress.getByName("localhost"), 1234);

                try {
                    socket.send(pacoteEnviar);
                } catch (IOException ex) {
                    Logger.getLogger(Client_3.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(Client_3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //cria n threads, cada thread vai gerar produto para um cliente
    public void criaThreads(int num) {

        ThreadCliente t[] = new ThreadCliente[num];
        int id = 1;
        for (int i = 0; i < num; i++) {
            t[i] = new ThreadCliente(id);
            t[i].start();
            id++;
        }
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        Client_3 c = new Client_3();
        c.criaThreads(3);

    }

}
