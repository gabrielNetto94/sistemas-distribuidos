
/*
Mensagens que o servidor recebe
a)Mensagem de identificação do cliente
"ADICIONA"

b)Mensagem com um ponto a ser desenhado
"PONTO:xxx;yyy;rrr;ggg;bbb"

Servidor:
    - lista de clientes (ip, porta)
    - lista de pontos
 */
package aulaudp;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    class Cliente {

        InetAddress ip;
        int porta;
    }

    class Ponto {

        int x, y;
        Color cor;
    }

    ArrayList<Cliente> listaClientes = new ArrayList<>();
    ArrayList<Ponto> listaPontos = new ArrayList<>();
    DatagramSocket socket;

    public void criaThread() {
        new Thread() {
            public void run() {
                while (true) {
                    //só monta a mensagem e só envia se houver pelo menos 1 cliente e 1 ponto
                    if (listaClientes.size() > 0 && listaPontos.size() > 0) {
                        //monta uma mensagem contendo os pontos da lista
                        String msg = "PONTOS:";
                        synchronized(listaPontos){
                            for (Ponto p : listaPontos) {
                                msg += p.x + ";" + p.y + ";" + p.cor.getRed() + ";" + p.cor.getGreen() + ";" + p.cor.getBlue() + "_";
                            }
                        }
                        System.out.println("Vou enviar: "+msg);
                        for (Cliente c : listaClientes) {
                            byte buffer[] = msg.getBytes();
                            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, c.ip, c.porta);
                            try {
                                socket.send(pacote);
                            } catch (IOException ex) {
                                System.out.println("Erro no envio dos pontos");
                            }
                        }
                    }
                    //dorme por meio segundo
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    public Servidor() {
        try {
            //cria o socket udp
            socket = new DatagramSocket(1234, InetAddress.getByName("localhost"));
            //cria uma thread para enviar a listaPontos para todos os clientes a cada x segundos
            criaThread();
            while (true) {
                //cria um pacote para receber mensagem
                byte buffer[] = new byte[30];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                try {
                    //aguarda um pacote chegar
                    socket.receive(pacote);
                    //extrair os dados do pacote
                    byte bufferRec[] = pacote.getData();
                    String msgRec = new String(bufferRec, 0, pacote.getLength());
                    System.out.println(msgRec);
                    if (msgRec.equals("ADICIONA")) {
                        //adiciona o cliente na lista de clientes
                        //extrai do pacote o ip e a porta do cliente
                        InetAddress ipCliente = pacote.getAddress();
                        int portaCliente = pacote.getPort();
                        Cliente cli = new Cliente();
                        cli.ip = ipCliente;
                        cli.porta = portaCliente;
                        listaClientes.add(cli);
                        System.out.println("Recebi um cliente");
                    } else if (msgRec.startsWith("PONTO")) {
                        //adiciona o ponto na lista de pontos
                        String partes[] = msgRec.split(":");
                        String dados[] = partes[1].split(";");
                        int x = Integer.parseInt(dados[0]);
                        int y = Integer.parseInt(dados[1]);
                        int r = Integer.parseInt(dados[2]);
                        int g = Integer.parseInt(dados[3]);
                        int b = Integer.parseInt(dados[4]);
                        Color cor = new Color(r, g, b);
                        Ponto p = new Ponto();
                        p.x = x;
                        p.y = y;
                        p.cor = cor;
                        synchronized(listaPontos){
                            listaPontos.add(p);
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Erro no recebimento de um pacote");
                }
            }
        } catch (UnknownHostException ex) {
            System.out.println("Erro na espeificação do endereço do servidor");
        } catch (SocketException ex) {
            System.out.println("Erro na criação do socket");
        }
    }

    public static void main(String[] args) {
        Servidor s = new Servidor();
    }
}