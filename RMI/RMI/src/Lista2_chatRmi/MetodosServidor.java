package Lista2_chatRmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MetodosServidor extends UnicastRemoteObject implements IMetodosServidor {

    ArrayList<IMetodosCliente> listaClientes = new ArrayList<>();
    ArrayList<String> cliConectados = new ArrayList<>();
    
    public MetodosServidor() throws RemoteException {

    }

    //método que o cliente invoca no servidor para se registrar
    @Override
    public void registraCliente(IMetodosCliente refCliente,String nome) throws RemoteException {
        
        listaClientes.add(refCliente);
        cliConectados.add(nome);
        
        System.out.println("Cliente "+nome+" conectado!");
        
        for (IMetodosCliente cli:listaClientes){
                cli.atualizaCliente(cliConectados);
        }
        
    }

    @Override
    public void recebeMensagem(String mensagem) throws RemoteException {        
        for (IMetodosCliente cli:listaClientes){
                cli.recebeMensagem(mensagem);
        }
    }

    @Override
    public void removeCliente(IMetodosCliente refCliente, String nome) throws RemoteException {
                
        listaClientes.remove(refCliente);
        cliConectados.remove(nome);
        
        for (IMetodosCliente cli:listaClientes){
                cli.atualizaCliente(cliConectados);
        }
        
    }


}
