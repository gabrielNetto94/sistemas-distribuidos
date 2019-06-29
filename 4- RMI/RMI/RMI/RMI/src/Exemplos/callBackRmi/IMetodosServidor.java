package Exemplos.callBackRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Descreve os métodos que o cliente pode invocar no servidor
*/
public interface IMetodosServidor extends Remote{
    
    public void registraCliente(IMetodosCliente refCliente) throws RemoteException;
}
