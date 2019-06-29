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
import java.util.Scanner;

public class Cliente_1 {

    public static void main(String[] args) throws SocketException {
        //cliente cria um socket sem pré definir uma porta ou ip
        DatagramSocket socket = new DatagramSocket();
        //mostra em qual ip e porta o socket foi criado
        System.out.println("Socket criado na porta " + socket.getLocalPort());
        while (true) {
            try {

                //###################### ENVIA MENSAGEM ###################
                Scanner entrada = new Scanner(System.in);

                System.out.println("Digite mensagem:");
                String msgEnviar = entrada.nextLine();

                //converte a string em bytes
                byte bufferEnviar[] = msgEnviar.getBytes();

                //coloca os bytes dentro de um pacote datagrama
                //o endereçamento da mensagem para o servidor vai no próprio pacote
                DatagramPacket pacoteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, InetAddress.getByName("localhost"), 1234);

                //envia a mensagem
                socket.send(pacoteEnviar);

                //###################### RECEBE MENSAGEM ##################
                
                //cria um pacote datagrama "vazio" para esperar uma mensagem chegar pela rede
                //com capacidade de até 100 bytes. Se passar de 10 bytes, a mensagem é cortada
                DatagramPacket pacoteRec = new DatagramPacket(new byte[100], 100);

                //aguarda uma mensagem chegar pela rede no pacote
                socket.receive(pacoteRec);//bloqueante

                //extrai os bytes do pacote recebido
                byte bufferRec[] = pacoteRec.getData();

                //converte os bytes pra String
                String msgRec = new String(bufferRec);

                //mostra na tela a String recebida
                System.out.println("Cliente recebeu: " + msgRec);

                
            } catch (SocketException ex) {
                System.out.println("Erro na criação do socket");
            } catch (IOException ex) {
                System.out.println("Erro no envio da mensagem");
            }
        }

    }
}
