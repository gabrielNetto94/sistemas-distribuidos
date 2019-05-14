package exemplo2;

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
    public double calcula(Operacao op) throws RemoteException {
        if(op.descricao.equals("soma")){
            return op.x+op.y;
        }
        else if(op.descricao.equals("subtracao")){
            return op.x-op.y;
        }
        else if(op.descricao.equals("divisao")){
            return op.x/op.y;
        }
        else if(op.descricao.equals("multiplicacao")){
            return op.x*op.y;
        }
        else if(op.descricao.equals("potencia")){
            return Math.pow(op.x, op.y);
        }
        else if(op.descricao.equals("raizQuadrada")){
            return Math.sqrt(op.x);
        }
        else{
            throw new UnsupportedOperationException("Operação não implementada");
        }
    }
    
    

    
}
