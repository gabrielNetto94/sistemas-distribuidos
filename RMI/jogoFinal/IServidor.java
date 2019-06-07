package jogoFinal;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidor extends Remote{
    
    public int registraCliente(ICliente cli) throws RemoteException;
    public void enviaPosicao(int linha, int coluna, int codigo) throws RemoteException;
    public void meTiraDaLista(int codigo, ICliente cli) throws RemoteException;
    public boolean posicaoValida(int linha, int coluna) throws RemoteException;
    
}
