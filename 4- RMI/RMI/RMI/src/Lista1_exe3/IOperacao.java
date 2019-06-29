package Lista1_exe3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOperacao extends Remote {

    public ContaCliente depositoObj(ContaCliente c, float valor) throws RemoteException;
    public ContaCliente saqueObj(ContaCliente c, float valor) throws RemoteException;
    public float saldoObj(ContaCliente c) throws RemoteException;
}
