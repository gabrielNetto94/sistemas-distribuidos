/*
3- Desenvolva uma aplicação RMI para um Banco. Ou seja, o servidor deverá implementar
um objeto remoto contendo uma variável que armazenará o saldo de uma conta, e também 
deverá implementar métodos para depositar, sacar e verificar o saldo.
 */
package Lista1_exe3;

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
            OperacaoBancaria op = new OperacaoBancaria();
            //registra o objeto acima em uma URL
            Naming.bind("rmi://localhost/OperacaoBancaria", op);

        } catch (RemoteException ex) {
            System.out.println("Exceção Remota");
        } catch (AlreadyBoundException ex) {
            System.out.println("Objeto remoto já foi registrado");
        } catch (MalformedURLException ex) {

            System.out.println("URL de registro do objeto está mal formada");
        }
    }
}
