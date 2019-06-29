
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) throws UnknownHostException, SocketException {
        //lista de clientes
        ArrayList<Socket> listaClientes = new ArrayList<>();

        
        //cria uma thread para esperar mensagens do cliente que se conectou
        Thread t = new Thread() {
            DatagramSocket socket = new DatagramSocket(1234, InetAddress.getByName("localhost"));

            public void run() {

                while (true) {
                    try {
                        DatagramPacket pacoteRec = new DatagramPacket(new byte[100], 100);

                        //aguarda uma mensagem chegar pela rede no pacote
                        socket.receive(pacoteRec);//bloqueante

                        //extrai os bytes do pacote recebido
                        byte bufferRec[] = pacoteRec.getData();
                        //converte os bytes pra String
                        String msgRec = new String(bufferRec);

                        //mostra na tela a String recebida
                        System.out.println("Servidor recebeu: " + msgRec);
                    } catch (IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();

    }
}

