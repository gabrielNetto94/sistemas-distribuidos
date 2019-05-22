package Lista2_chatRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Descreve os métodos que o servidor pode invocar nos clientes
*/
public interface IMetodosCliente extends Remote{
    
    public void atualizaCliente(String horario) throws RemoteException;
    public void recebeMensagem(String mensagem) throws RemoteException;
    public void atualizaClienteConectados(String mensagem) throws RemoteException;
    
}
