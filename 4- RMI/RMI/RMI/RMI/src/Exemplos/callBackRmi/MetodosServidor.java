package Exemplos.callBackRmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class MetodosServidor extends UnicastRemoteObject implements IMetodosServidor{

    ArrayList<IMetodosCliente> listaClientes = new ArrayList<>();
    
    public MetodosServidor() throws RemoteException{
        
    }
    //método que o cliente invoca no servidor para se registrar
    @Override
    public void registraCliente(IMetodosCliente refCliente) throws RemoteException {
        listaClientes.add(refCliente);
        System.out.println("Cliente se conectou");
    }
    
    public void inicializa(){
        while(true){
            try {
                Date horarioAtual = new Date();
                System.out.println("Vou enviar para os clientes: "+horarioAtual.toString());
                //invoca o enviaHorarioParaCliente em todos os clientes
                for(IMetodosCliente cli: listaClientes){
                    try {
                        cli.enviaHorarioParaCliente(horarioAtual.toString());
                    } catch (RemoteException ex) {
                        System.out.println("Falhou algum cliente");
                    }
                }
                //dorme por um tempo
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println("Falhou o sleep");
            }
        }
    }
    
}
