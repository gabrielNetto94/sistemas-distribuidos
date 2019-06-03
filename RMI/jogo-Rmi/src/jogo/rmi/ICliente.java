package jogo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ICliente extends Remote {
    public void recebePosicao(int linha, int coluna, int codigo) throws RemoteException;
    public void liberaPosicao(int codigo) throws RemoteException;
    public void atualizaJogadores(int numJogadores) throws RemoteException;
    
}
