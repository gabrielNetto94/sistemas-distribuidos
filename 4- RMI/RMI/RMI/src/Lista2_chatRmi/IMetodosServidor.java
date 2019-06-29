package Lista2_chatRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Descreve os métodos que o cliente pode invocar no servidor
*/
public interface IMetodosServidor extends Remote{
    
    public void registraCliente(IMetodosCliente refCliente,String nome) throws RemoteException;
    public void recebeMensagem(String mensagem) throws RemoteException;
    public void removeCliente(IMetodosCliente refCliente,String nome) throws RemoteException;

}
