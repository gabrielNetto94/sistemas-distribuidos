package jogoFinal;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends UnicastRemoteObject implements IServidor {

    int codigoAtual = 0;
    int maxJogadoresAtivos = 10;
    ArrayList<ICliente> listaClientes = new ArrayList<>();
    ArrayList<ClientePosicao> listaPosicoes = new ArrayList<>();
    LinkedList<Integer> listaCodigos = new LinkedList<>();

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
        ClientePosicao cliTemp = null;

        listaClientes.remove(cli);

        for (ClientePosicao p : listaPosicoes) {
            if (p.codigo == codigo) {
                cliTemp = p;//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                listaPosicoes.remove(p);
                break;
            }
        }

        listaCodigos.add(codigo);

        for (ICliente c : listaClientes) {
            c.updateTextArea(codigo);
        }
        enviaNumJogadores();

    }

    @Override
    public int registraCliente(ICliente cli) throws RemoteException {
        int codigoLista = 0;
        if (listaClientes.size() < maxJogadoresAtivos) {
            listaClientes.add(cli);
        } else {
            return -1;
        }

        for (ClientePosicao p2 : listaPosicoes) {
            cli.recebePosicao(p2.linha, p2.coluna, p2.codigo);
        }
        ClientePosicao p = new ClientePosicao();

        //antes de pegar o codigo atual, verifica se tem alguem na lista de codigo menor que ele
        //teste inicio
        listaCodigos.sort(null);
        for(Integer k:listaCodigos){
            System.out.println("Lista codigos: "+k);
        }
        
        if (!listaCodigos.isEmpty()) {
            codigoAtual++;
            if (codigoAtual < listaCodigos.getFirst()) {
                
                p.codigo = codigoAtual;
            } else {
                p.codigo = listaCodigos.getFirst();
                //remove o valor da lista
                listaCodigos.removeFirst();
            }
        }else{
            codigoAtual++;
            p.codigo = codigoAtual;
        }

        //teste fim
//        codigoLista = verificarListaCodigo();
//        if (codigoLista < codigoAtual) {
//
//            p.codigo = codigoLista;
//
//            //remove o valor da lista
//            removeListaCodigos(codigoLista);
//
//        } else {
//            codigoAtual++;
//            p.codigo = codigoAtual;
//        }
        //se não setar um valor o JAVA coloca como 0
        p.coluna = -1;
        p.linha = -1;
        listaPosicoes.add(p);

        enviaNumJogadores();

        return p.codigo;

    }

    @Override
    public void enviaPosicao(int linha, int coluna, int codigo) throws RemoteException {

        for (int i = 0; i < listaPosicoes.size(); i++) {
            ClientePosicao p = listaPosicoes.get(i);
            if (p.codigo == codigo) {
                p.linha = linha;
                p.coluna = coluna;
            }
//            System.out.println(p.codigo + ": " + p.linha + "," + p.coluna);
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

            Random r = new Random();
            ClientePosicao cli = new ClientePosicao();
            cli.codigo = 11;

            do {
                cli.linha = r.nextInt(15);
                cli.coluna = r.nextInt(35);
            } while (!posicaoValida(cli.linha, cli.coluna));

//            System.out.println("Add moeda na lista");
            listaPosicoes.add(cli);

//            System.out.println("envia posicao da moeda para clientes");
            enviaPosicao(cli.linha, cli.coluna, cli.codigo);

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

    private int verificarListaCodigo() {
        int codigo = 500;

        if (listaCodigos.isEmpty()) {
            return codigo;
        }
        //percorre e acha o menor
        for (int i = 0; i < listaCodigos.size(); i++) {
            if (listaCodigos.get(i) < codigo) {
                codigo = listaCodigos.get(i);
            }
        }
        System.out.println("o menor numero é: " + codigo);

        return codigo;
    }

    private void removeListaCodigos(int codigoLista) {

        for (int i = 0; i < listaCodigos.size(); i++) {
            if (listaCodigos.get(i) == codigoLista) {
                listaCodigos.remove(i);
                break;
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
