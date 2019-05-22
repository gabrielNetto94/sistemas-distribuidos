package Exemplos.callBackRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Descreve os métodos que o servidor pode invocar nos clientes
*/
public interface IMetodosCliente extends Remote{
    
    public void enviaHorarioParaCliente(String horario) throws RemoteException;
}
