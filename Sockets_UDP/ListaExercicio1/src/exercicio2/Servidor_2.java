package exercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Servidor_2 {

    public static void main(String[] args) throws UnknownHostException, SocketException {

        ArrayList<Integer> portaClientes = new ArrayList<>();

        //criação de um socket em uma porta e ip pré-definidos
        DatagramSocket socket = new DatagramSocket(1234, InetAddress.getByName("localhost"));
        while (true) {
            try {
                DatagramPacket pacoteRec = new DatagramPacket(new byte[100], 100);
                //aguarda uma mensagem chegar pela rede no pacote
                socket.receive(pacoteRec);//bloqueante
                //extrai os bytes do pacote recebido
                byte bufferRec[] = pacoteRec.getData();
                //converte os bytes pra String
                String msgRec = new String(bufferRec);
                
                //pega ip e porta do cliente que enviou a mensagem
                InetAddress ipCliente = pacoteRec.getAddress();
                int portaCliente = pacoteRec.getPort();
                
                //mostra na tela a String recebida
                System.out.println("Servidor recebeu: "+msgRec+" Cliente ip: " + ipCliente.getHostName() + ", porta: " + portaCliente);

                    
                //pega a porta do cliente que enviou a mensagem
                if (!portaClientes.contains(pacoteRec.getPort())) {
                    System.out.println("porta "+pacoteRec.getPort()+" adicionada");
                    portaClientes.add(pacoteRec.getPort());
                }
                
                //percorre a lista de clientes, pela porta e envia a mensagem
                for (int i = 0; i < portaClientes.size(); i++) {
                    byte bufferResposta[] = msgRec.getBytes();  
                    DatagramPacket pacoteResposta = new DatagramPacket(msgRec.getBytes(), msgRec.length(), ipCliente, portaClientes.get(i));
                    socket.send(pacoteResposta);
                }

                
            } catch (UnknownHostException ex) {
                System.out.println("Erro de endereçamento do servidor");
            } catch (SocketException ex) {
                System.out.println("Erro de porta (porta já utilizada, provavelmente");
            } catch (IOException ex) {
                System.out.println("Problema de E/S");
            }
        }
    }

}
