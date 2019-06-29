package Lista2_chatRmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class MetodosServidor extends UnicastRemoteObject implements IMetodosServidor {

    ArrayList<IMetodosCliente> listaClientes = new ArrayList<>();

    public MetodosServidor() throws RemoteException {

    }

    //método que o cliente invoca no servidor para se registrar
    @Override
    public void registraCliente(IMetodosCliente refCliente,String nome) throws RemoteException {
        listaClientes.add(refCliente);
        System.out.println("Cliente se conectou");
        
        for (IMetodosCliente cli:listaClientes){
                cli.atualizaClienteConectados(nome+"");
        }
        
    }

    @Override
    public ArrayList getListaCliente() throws RemoteException {
        //enviar a lista de clientes conectados para o cliente
        return this.listaClientes;
    }

    @Override
    public void recebeMensagem(String mensagem) throws RemoteException {
        System.out.println("Mensagem cliente: " + mensagem);
        
        for (IMetodosCliente cli:listaClientes){
                cli.atualizaCliente(mensagem);
        }
    }

    

}
