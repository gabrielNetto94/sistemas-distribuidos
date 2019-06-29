package Lista2_chatRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
Descreve os métodos que o servidor pode invocar nos clientes
*/
public interface IMetodosCliente extends Remote{
    
    public void recebeMensagem(String mensagem) throws RemoteException;
    public void atualizaCliente(ArrayList listaConectados) throws RemoteException;
       
}
