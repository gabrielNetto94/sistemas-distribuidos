package Exemplos.callBackRmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MetodosCliente extends UnicastRemoteObject implements IMetodosCliente {

    public MetodosCliente() throws RemoteException{
        
    }
    //método que o servidor invocará no cliente para passar o horário atual do sistema
    @Override
    public void enviaHorarioParaCliente(String horario) throws RemoteException {
        System.out.println("Servidor atualizou meu relógio: "+horario);
    }
    
}
