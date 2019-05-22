package Lista2_chatRmi;

import Exemplos.callBackRmi.MetodosCliente;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
Descreve os métodos que o cliente pode invocar no servidor
*/
public interface IMetodosServidor extends Remote{
    
    public void registraCliente(IMetodosCliente refCliente,String nome) throws RemoteException;
    public void recebeMensagem(String mensagem) throws RemoteException;
    public ArrayList getListaCliente()throws RemoteException;

}
