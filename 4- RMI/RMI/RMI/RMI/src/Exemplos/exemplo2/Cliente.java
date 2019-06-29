package Exemplos.exemplo2;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {
        try {
            //cliente busca uma referencia da calculadora por meio
            //da sua URL
            ICalculadora ref = (ICalculadora) Naming.lookup("rmi://localhost/Calculadora");
            
            //invoca os métodos
            
            Operacao op1 = new Operacao();
            op1.descricao = "soma";
            op1.x = 5;
            op1.x = 10;
            double res = ref.calcula(op1);
            System.out.println("Soma = "+res);
            
            Operacao op2 = new Operacao();
            op2.descricao = "raizQuadrada";
            op2.x = 25;
            res = ref.calcula(op2);
            System.out.println("Raiz = "+res);
                        
        } catch (NotBoundException ex) {
            System.out.println("Erro: provavelmente não tem nada associado a URL especificada");
        } catch (MalformedURLException ex) {
            System.out.println("Erro: URL mal formada");
        } catch (RemoteException ex) {
            System.out.println("Erro: Erro de comunicação");
        }
    }
}