package Lista1_exe4;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMetodosServidor extends Remote {

    public ArrayList arquivoLer(String path) throws RemoteException;

    public String arquivoLerLinha(String path, int numeroLinha) throws RemoteException;

    public void arquivoEscreve(String path, String texto) throws RemoteException;

    public void arquivoDeletar(String path) throws RemoteException;
}
