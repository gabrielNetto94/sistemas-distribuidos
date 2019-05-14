package Lista1_exe3;

import Lista1_exe2.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConversor extends Remote {

    public float conversao(String moedaOrigem, String moedaDestino, float valor) throws RemoteException;

}
