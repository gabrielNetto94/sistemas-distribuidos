package Exemplos.exemplo1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
    Especificação dos métodos remotos (descrição)
    1 - a interface deve ter extends Remote
    2 - todos os métodos devem ser públicos
    3 - todos os métodos devem disparar (throws) exceção remota, para 
    obrigar o cliente a chamar esses metodos dentro de um try catch

    4 - se for colocar javadoc (documentação), ela precisa ser feita
    aqui na interface
*/
public interface ICalculadora extends Remote {
    /**
     * Esse método faz a soma
     * @param x primeiro parametro da soma
     * @param y segundo parametro da soma
     * @return retorna a soma de x e y
     * @throws RemoteException 
     */
    public double soma(double x, double y) throws RemoteException;
    /**
     * Esse método faz a subtração
     * @param x primeiro parametro da subtração
     * @param y segundo parametro da subtração
     * @return retorna a diferença entre x e y
     * @throws RemoteException 
     */
    public double subtracao(double x, double y) throws RemoteException;
    public double divisao(double x, double y) throws RemoteException;
    public double multiplicacao(double x, double y) throws RemoteException;
    public double potencia(double base, double expoente) throws RemoteException;
    public double raizQuadrada(double x) throws RemoteException;
}