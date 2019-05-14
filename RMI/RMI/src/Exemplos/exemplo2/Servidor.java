package exemplo2;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) {
        try {
            //inicialização do rmiregistry, que é o serviço que
            //o servidor usa pra registrar objetos remotos
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            //instancia uma calculadora
            Calculadora c = new Calculadora();
            //registra o objeto acima em uma URL
            Naming.bind("rmi://localhost/Calculadora", c);
            
            
        } catch (RemoteException ex) {
            System.out.println("Exceção Remota");
        } catch (AlreadyBoundException ex) {
            System.out.println("Objeto remoto já foi registrado");
        } catch (MalformedURLException ex) {
            System.out.println("URL de registro do objeto está mal formada");
        }
    }
}
