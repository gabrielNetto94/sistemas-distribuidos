package exercicio3;

import java.io.IOException;
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
        enviaMensagem();

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

    public void enviaMensagem() throws UnknownHostException, SocketException {
        Thread t = new Thread() {
            Random random = new Random();

            public void run() {
                int i = 0;
//                while (true) {
                while (i < 10) {
                    try {
                        String msgEnviar
                                = Integer.toString(random.nextInt(10)) + ':'
                                +//gera aleatorio para idCliente
                                nomesProdutos[random.nextInt(nomesProdutos.length)] + ':'
                                + //pega o nome de algum produto aleatório
                                Integer.toString(random.nextInt(10) + 1);//gera qtdVenda aleatório

                        System.out.println(msgEnviar);//teste

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
            }
        };
        t.start();
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        Client_3 c = new Client_3();

    }

}
