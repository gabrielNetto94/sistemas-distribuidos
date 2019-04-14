/*
 Modifique o Chat TCP de modo que: a) seja apresentada, na interface gráfica do cliente, 
 uma lista dos clientes (nicks) que estão conectados ao chat naquele momento; 
 b) a possibilidade de enviar mensagens privadas de um cliente para outro.
 */
package Desafio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public Servidor() {
        //lista de clientes armazenando o objeto Socket
        final ArrayList<Socket> listaClientes = new ArrayList<>();
        //lista de clientes armazenando a string nick
        final ArrayList<String> clientesConectados = new ArrayList<String>();

        try {
            //criação do servidor
            ServerSocket servidor = new ServerSocket(1234);

            while (true) {
                //aguarda um cliente se conectar
                final Socket cliente = servidor.accept();//bloqueante
                System.out.println("Recebi um cliente");

                //coloca o cliente em uma lista
                listaClientes.add(cliente);

                //cria uma thread para esperar mensagens do cliente que se conectou
                //thread broadcast
                Thread t = new Thread() {
                    public void run() {
                        try {
                            DataInputStream in = new DataInputStream(cliente.getInputStream());
                            while (true) {
                                //espera uma mensagem do cliente do tipo String (UTF)
                                String mensagem = in.readUTF();//bloqueante

                                // se 1º char for # é nick do cliente para adicionar na lista
                                if (mensagem.charAt(0) == '#') {
                                    String[] nick = mensagem.split("#");
                                    System.out.println(nick[1]);
                                    if (!clientesConectados.contains(nick[1])) {
                                        clientesConectados.add(nick[1]);
                                    }

                                    //envia para cada clienteConectado a lista de clientes
                                    for (Socket cliEnviar : listaClientes) {
                                        DataOutputStream out = new DataOutputStream(cliEnviar.getOutputStream());
                                        //envia a lista de clientes conectados para o cliente exibir no jTextArea
                                        for (int i = 0; i < clientesConectados.size(); i++) {
                                            out.writeUTF("#" + clientesConectados.get(i));
                                        }
                                    }
                                }

                                //mensagem PRIVADA
                                if (mensagem.charAt(0) == '@') {

                                    //split da mensagem em origem, destino e mensagem
                                    String[] nickPrivado = mensagem.split("@");
                                    String origem = nickPrivado[1];
                                    String destino = nickPrivado[2];
                                    String msg = nickPrivado[3];

                                    //teste para ver se cliente existe na lista de conectados
                                    if (!clientesConectados.contains(origem) || !clientesConectados.contains(destino)) {
                                        System.out.println("Origem ou Destino não existe na lista de clientes");
                                    } else {
                                        //percorre todos os clientes para enviar mensagem privada
                                        for (Socket listaCliente : listaClientes) {
                                            DataOutputStream out = new DataOutputStream(listaCliente.getOutputStream());
                                            //System.out.println("Enviando para os clientes->" + '@' + origem + '@' + destino + '@' + msg);
                                            out.writeUTF('@' + origem + '@' + destino + '@' + msg);
                                        }
                                    }
                                }

                                //mensagem CHAT
                                if (mensagem.charAt(0) != '@' && mensagem.charAt(0) != '#') {
                                    for (Socket cliEnviar : listaClientes) {
                                        DataOutputStream out = new DataOutputStream(cliEnviar.getOutputStream());
                                        out.writeUTF(mensagem);
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            System.out.println("Erro ao extrair o input stream do cliente");
                        }
                    }
                };
                t.start();
            }

        } catch (IOException ex) {
            System.out.println("Porta 1234 já está em uso");
        }

    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
    }
}
