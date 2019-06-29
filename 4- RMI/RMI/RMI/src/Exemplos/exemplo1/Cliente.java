package Exemplos.exemplo1;

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
            double res = ref.soma(5, 10);
            System.out.println("Soma = "+res);
            
            res = ref.raizQuadrada(25);
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
