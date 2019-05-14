package Lista1_exe3;

import Exemplos.exemplo2.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {
        try {
            //cliente busca uma referencia da calculadora por meio
            //da sua URL
            IConversor ref = (IConversor) Naming.lookup("rmi://localhost/Conversor");
            
            //invoca os métodos
            float resultado = ref.conversao("real", "dólar", 1);
            System.out.println(resultado);
            
        } catch (NotBoundException ex) {
            System.out.println("Erro: provavelmente não tem nada associado a URL especificada");
        } catch (MalformedURLException ex) {
            System.out.println("Erro: URL mal formada");
        } catch (RemoteException ex) {
            System.out.println("Erro: Erro de comunicação");
        }
    }
}
