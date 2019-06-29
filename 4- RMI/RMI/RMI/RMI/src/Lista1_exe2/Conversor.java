package Lista1_exe2;

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
public class Conversor extends UnicastRemoteObject implements IConversor {

    public Conversor() throws RemoteException{
        
    }
    
    @Override
    public float conversao(String moedaOrigem, String moedaDestino, float valor) throws RemoteException {
        if(moedaOrigem.equals("dólar") && moedaDestino.equals("real")){
            return (float) (valor*3.99);
        }
        if(moedaOrigem.equals("real") && moedaDestino.equals("dólar")){
            return (float) (valor/3.99);
        }
        
        if(moedaOrigem.equals("dólar") && moedaDestino.equals("euro")){
            return (float) (valor/1.12);
        }
        if(moedaOrigem.equals("euro") && moedaDestino.equals("dólar")){
            return (float) (valor*1.12);
        }
        
        if(moedaOrigem.equals("euro") && moedaDestino.equals("real")){
            return (float) (valor*4.49);
        }
        if(moedaOrigem.equals("real") && moedaDestino.equals("euro")){
            return (float) (valor/4.49);
        }
        
        return -1;
    }
    
    

    
}
