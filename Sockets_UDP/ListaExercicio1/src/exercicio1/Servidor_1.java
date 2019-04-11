/*1) Utilizando Sockets UDP, desenvolva um aplicação Servidora que receba uma String de um cliente,
 inverta a mesma, e devolva o resultado para o cliente. O servidor deve estar sempre pronto para 
 receber novas mensagens. O servidor precisará descobrir, através do pacote enviado pelo cliente,
 o seu endereço ip + porta, para que seja possível retornar o resultado para o cliente.
 */
package exercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Servidor_1 {

    public static void main(String[] args) throws UnknownHostException, SocketException {
                        //criação de um socket em uma porta e ip pré-definidos
        DatagramSocket socket = new DatagramSocket(1234, InetAddress.getByName("localhost"));
        while (true) {
            try {
                //###################### RECEBE MENSAGEM ##################
                
                //cria um pacote datagrama "vazio" para esperar uma mensagem chegar pela rede
                //com capacidade de até 100 bytes. Se passar de 100 bytes, a mensagem é cortada
                DatagramPacket pacoteRec = new DatagramPacket(new byte[100], 100);

                //aguarda uma mensagem chegar pela rede no pacote
                socket.receive(pacoteRec);//bloqueante

                //extrai os bytes do pacote recebido
                byte bufferRec[] = pacoteRec.getData();
                //converte os bytes pra String
                String msgRec = new String(bufferRec);

                //mostra na tela a String recebida
                System.out.println("Servidor recebeu: " + msgRec);

                //tira os campo null da String
                char[] a = msgRec.toCharArray();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < msgRec.length(); i++) {
                    if (a[i] == ' ') {

                    } else {
                        sb.append(a[i]);
                    }
                }

                System.out.println("String sem espaços-> " + sb.toString());
                //inverte a palavra
                String fraseInvertida = new StringBuilder(sb.toString()).reverse().toString();
                System.out.println("palavra invertida ->" + fraseInvertida);

                //###################### ENVIA MENSAGEM ###################
                
                //converte a mensagem de resposta em bytes
                byte bufferResposta[] = fraseInvertida.getBytes();

                //descobre quem é o cliente por meio do pacote recebido anteriormente
                InetAddress ipCliente = pacoteRec.getAddress();

                int portaCliente = pacoteRec.getPort();
                //mostra quem foi o cliente que mandou uma mensagem
                System.out.println("Cliente ip: " + ipCliente.getHostName() + ", porta: " + portaCliente);

                //monta um pacote datagrama com a resposta
                DatagramPacket pacoteResposta = new DatagramPacket(fraseInvertida.getBytes(), fraseInvertida.length(), ipCliente, portaCliente);
                //envia o pacote de resposta
                socket.send(pacoteResposta);

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
