package jogo.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends UnicastRemoteObject implements IServidor {

    int codigoAtual = 0;
    int maxJogadoresAtivos = 10;
    ArrayList<ICliente> listaClientes = new ArrayList<>();
    ArrayList<ClientePosicao> listaPosicoes = new ArrayList<>();

    public Servidor() throws RemoteException {
        new Thread() {

            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(3000);
                        gerarMoeda();                        

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }.start();
    }

    @Override
    public void meTiraDaLista(int codigo, ICliente cli) throws RemoteException {
        //cria um cliente temporário para guardar a posição X Y 
        ClientePosicao cliTemp;

        listaClientes.remove(cli);

        for (ClientePosicao p : listaPosicoes) {
            if (p.codigo == codigo) {
                cliTemp = p;
                listaPosicoes.remove(p);
                break;
            }
        }

        enviaNumJogadores();

    }

    @Override
    public int registraCliente(ICliente cli) throws RemoteException {

        if (listaClientes.size() < maxJogadoresAtivos) {
            listaClientes.add(cli);
        } else {
            return -1;
        }

        for (ClientePosicao p2 : listaPosicoes) {
            cli.recebePosicao(p2.linha, p2.coluna, p2.codigo);
        }
        ClientePosicao p = new ClientePosicao();
        codigoAtual++;
        p.codigo = codigoAtual;
        //se não setar um valor o JAVA coloca como 0
        p.coluna = -1;
        p.linha = -1;
        listaPosicoes.add(p);

        enviaNumJogadores();

        return p.codigo;

    }

    @Override
    public void enviaPosicao(int linha, int coluna, int codigo) throws RemoteException {
        //atualiza a posição do cliente na lista de posições
//        System.out.println("Lista atualizada:");
        for (int i = 0; i < listaPosicoes.size(); i++) {
            ClientePosicao p = listaPosicoes.get(i);
            if (p.codigo == codigo) {
                p.linha = linha;
                p.coluna = coluna;
            }
            System.out.println(p.codigo + ": " + p.linha + "," + p.coluna);
        }
        for (ICliente cli : listaClientes) {
            cli.recebePosicao(linha, coluna, codigo);
        }
    }

    @Override
    public boolean posicaoValida(int linha, int coluna) throws RemoteException {

        for (ClientePosicao posicao : listaPosicoes) {
            if (posicao.linha == linha && posicao.coluna == coluna) {
                return false;
            }
        }

        return true;
    }

    public void gerarMoeda() {
        
        try {
            //remove da lista para adicionar outra posicao
//            for (ClientePosicao p : listaPosicoes) {
//                if (p.codigo == 11) {
//                    System.out.println("Removendo moeda");
//                    listaPosicoes.remove(p);
//                }
//            }

            Random r = new Random();
            ClientePosicao cli = new ClientePosicao();
            cli.codigo = 11;

            do {
                cli.linha = r.nextInt(15);
                cli.coluna = r.nextInt(35);
            } while (!posicaoValida(cli.linha, cli.coluna));

            System.out.println("Add moeda na lista");
            listaPosicoes.add(cli);

            System.out.println("envia posicao da moeda para clientes");
            enviaPosicao(cli.linha, cli.coluna, cli.codigo);

//            try {
//                while (!posicaoValida(cli.linha, cli.coluna)) {
//                    cli.linha = r.nextInt(15);
//                    cli.coluna = r.nextInt(35);
//                }
//            } catch (RemoteException ex) {
//                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void enviaNumJogadores() {
        for (ICliente cli : listaClientes) {
            try {
                cli.atualizaJogadores(listaClientes.size());
            } catch (RemoteException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class ClientePosicao {

        int linha, coluna, codigo;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Servidor s = new Servidor();
            Naming.bind("rmi://localhost/Servidor", s);
        } catch (AlreadyBoundException e) {
            System.out.println("Objeto já registrado");
        } catch (RemoteException ex) {
            System.out.println("Erro de comunicação");
        } catch (MalformedURLException ex) {
            System.out.println("URL mal formada");
        }

    }
}
