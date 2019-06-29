/*3) Faça uma aplicação cliente-servidor em que o servidor mantem uma lista de produtos 
e seus respectivos preços. Os clientes então começam a enviar para o servidor vários 
datagramas indicando suas vendas, com mensagens no formato:  
<identificador_do_cliente>:<nome_do_produto>:<quantidade_vendida>
1:Farinha de Trigo:20
2:Leite:30
2:Batata:15
1:Azeite:20
1:Batata:30
O servidor ficará recebendo mensagens de vários clientes e ir calculando o quanto cada um vendeu.
Quando os clientes terminarem de enviar ao servidor os datagramas dos produtos vendidos, 
eles enviam uma mensagem indicando que finalizou o envio com o texto 
“<identificador_do_cliente>:LISTAGEM FINALIZADA”. O servidor, ao identificar que 
recebeu a mensagem de finalização, deve enviar a esse cliente uma mensagem indicando o valor total das vendas.
 */
package exercicio3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor_3 {

    ArrayList<Cliente> clientes = new ArrayList();

    public String produto[] = {
        "Tomate;3.5",
        "Arroz;5.75",
        "Feijão;6.99",
        "Alface;1.59",
        "Carne;10.99",
        "Batata;2.99"};

    public Servidor_3() throws UnknownHostException, SocketException {

        //inica o método para receber os dados do cliente
        threadRecebeDados();
    }

    DatagramSocket socket = new DatagramSocket(1234, InetAddress.getByName("localhost"));

    public static void main(String[] args) {
        try {
            Servidor_3 s = new Servidor_3();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void threadRecebeDados() {

        Thread t = new Thread() {
            @Override
            public void run() {

                while (true) {
                    try {

                        float valorProduto = 0; //pega na lista de produtos
                        int idCliente; //pega no split da mensagem
                        String nomeProduto; //pega no split da mensagem
                        int qtdVendida; //pega no split da mensagem

                        DatagramPacket pacoteRec = new DatagramPacket(new byte[100], 100);

                        //aguarda uma mensagem chegar pela rede no pacote
                        socket.receive(pacoteRec);//bloqueante

                        //extrai os bytes do pacote recebido
                        byte bufferRec[] = pacoteRec.getData();
                        //converte os bytes pra String
                        String msgRec = new String(bufferRec);

                        //
                        if (msgRec.charAt(0) == '<') {
                            System.out.println(" FINALIZOU O ENVIO!!");

                            // IGNORA OS CARACTER E PEGA SÓ OS NUMEROS
                            StringBuffer sb = new StringBuffer();
                            for (Character caracter : msgRec.toCharArray()) {
                                if (Character.isDigit(caracter)) {
                                    sb.append(caracter);
                                }
                            }
                            String oi = sb.toString();
                            int idCli = Integer.parseInt(oi);
                            //passa o numero para o método finalizaCliente, que manda uma mensagem para o Cliente dizendo qual cliente finalizou
                            finalizaCliente(idCli);

                        }

                        if (msgRec.charAt(0) != '<') {
                            System.out.println(msgRec);

                            String msgSplit[] = msgRec.split(":");
                            idCliente = Integer.parseInt(msgSplit[0]);
                            nomeProduto = msgSplit[1];
                            qtdVendida = (int) Float.parseFloat(msgSplit[2]);

                            //se o método clienteNaLista retornar falso, insere o cliente na lista
                            if (!clienteNaLista(idCliente)) {
                                Cliente c = new Cliente(idCliente, 0);//iniciar com saldo 0
                                clientes.add(c);
                            }

                            //percorre o vetor de string e pega o valor do produto
                            for (int i = 0; i < produto.length; i++) {
                                String split[] = produto[i].split(";");
                                if (nomeProduto.equals(split[0])) {
                                    valorProduto = Float.parseFloat(split[1]);
                                }
                            }

                            //foreach que percorre o cliente para alterar a variável total  =  total + (qtdVenvida * valorProduto)
                            for (Cliente cliente : clientes) {
                                if (idCliente == cliente.getIdCliente()) {
                                    cliente.total = cliente.total + (qtdVendida * valorProduto);/*valor do produto*/
                                }
                            }
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();
    } //

    public void finalizaCliente(int idCliente) throws IOException {
        float total = 0;
        String msgEnviar;
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                total = cliente.getTotal();
            }
        }

        msgEnviar = "Cliente " + idCliente + " Total de vendas R$ " + total;
        byte bufferEnviar[] = msgEnviar.getBytes();
        DatagramPacket pacoteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, InetAddress.getByName("localhost"), 1234);
        socket.send(pacoteEnviar);
    }

    public boolean clienteNaLista(int idCliente) {
        if (clientes.isEmpty()) {
            return false;
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }

    class Produto {

        String nomeProduto;
        float preco;

        public Produto(String nomeProduto, float preco) {
            this.nomeProduto = nomeProduto;
            this.preco = preco;
        }

        public String getNomeProduto() {
            return nomeProduto;
        }

        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
        }

        public float getPreco() {
            return preco;
        }

        public void setPreco(float preco) {
            this.preco = preco;
        }

    }

    class Cliente {

        int idCliente;
        float total;

        public Cliente(int idCliente, float total) {
            this.idCliente = idCliente;
            this.total = total;
        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

    }

}
