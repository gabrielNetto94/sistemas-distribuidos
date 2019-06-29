package Lista1_exe3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperacaoBancaria extends UnicastRemoteObject implements IOperacao {

    public OperacaoBancaria() throws RemoteException {

    }


    @Override
    public ContaCliente depositoObj(ContaCliente c, float valor) throws RemoteException {
        c.setSaldo(c.getSaldo() + valor);
        return c;
    }

    @Override
    public ContaCliente saqueObj(ContaCliente c, float valor) throws RemoteException {
        c.setSaldo(c.getSaldo() - valor);
        return c;
    }

    @Override
    public float saldoObj(ContaCliente c) throws RemoteException {
        return c.getSaldo();
    }
}
