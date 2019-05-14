package Exemplos.exemplo1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
    Implementação dos métodos remotos
    1 - especificar qual interface essa classe implementa
    (implements ICalculadora)
    2 - colocar extends UnicastRemoteObject
    3 - implementar o construtor da classe, mesmo que não tenha nada implementado nele,
    com public e throws RemoteException também
*/
public class Calculadora extends UnicastRemoteObject implements ICalculadora {

    public Calculadora() throws RemoteException{
        
    }
    
    @Override
    /*
    teste
    */
    public double soma(double x, double y) throws RemoteException {
        return x+y;
    }

    @Override
    public double subtracao(double x, double y) throws RemoteException {
        return x-y;
    }

    @Override
    public double divisao(double x, double y) throws RemoteException {
        return x/y;
    }

    @Override
    public double multiplicacao(double x, double y) throws RemoteException {
        return x*y;
    }

    @Override
    public double potencia(double base, double expoente) throws RemoteException {
        double res = Math.pow(base, expoente);
        return res;
    }

    public double raizQuadrada(double x) throws RemoteException {
        double res = Math.sqrt(x);
        return res;
    }
    
}
