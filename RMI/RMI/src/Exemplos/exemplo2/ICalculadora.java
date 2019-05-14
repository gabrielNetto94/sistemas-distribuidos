package exemplo2;

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
    
    public double calcula(Operacao op) throws RemoteException;
}