package jogormi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Servidor extends UnicastRemoteObject implements IServidor {

    int codigoAtual = 0;
    ArrayList<ICliente> listaClientes = new ArrayList<>();
    ArrayList<ClientePosicao> listaPosicoes = new ArrayList<>();

    @Override
    public void meTiraDaLista(int codigo, ICliente cli) throws RemoteException {
        listaClientes.remove(cli);
        for (ClientePosicao p : listaPosicoes) {
            if(p.codigo == codigo){
                listaPosicoes.remove(p);
                break;
            }
        }
        for (ICliente cli2 : listaClientes) {
            cli2.liberaPosicao(codigo);
        }
    }

    class ClientePosicao {

        int linha, coluna, codigo;
    }

    public Servidor() throws RemoteException {

    }

    @Override
    public int registraCliente(ICliente cli) throws RemoteException {
        listaClientes.add(cli);
        for (ClientePosicao p2 : listaPosicoes) {
            cli.recebePosicao(p2.linha, p2.coluna, p2.codigo);
        }
        ClientePosicao p = new ClientePosicao();
        codigoAtual++;
        p.codigo = codigoAtual;
        listaPosicoes.add(p);
        return p.codigo;
    }

    @Override
    public void enviaPosicao(int linha, int coluna, int codigo) throws RemoteException {
        //atualiza a posição do cliente na lista de posições
        System.out.println("Lista atualizada:");
        for (int i = 0; i < listaPosicoes.size(); i++) {
            ClientePosicao p = listaPosicoes.get(i);
            if (p.codigo == codigo) {
                p.linha = linha;
                p.coluna = coluna;
            }
            System.out.println(p.codigo+": "+p.linha+","+p.coluna);
        }
        for (ICliente cli : listaClientes) {
            cli.recebePosicao(linha, coluna, codigo);
        }
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
