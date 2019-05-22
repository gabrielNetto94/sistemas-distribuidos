package Lista1_exe3;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {

            IOperacao ref = (IOperacao) Naming.lookup("rmi://localhost/OperacaoBancaria");
            ContaCliente c = new ContaCliente();

            Scanner scan = new Scanner(System.in);

            int op = 0;

            while (op != 4) {
                System.out.println("1 - Depósito \n2 - Saque \n3 - Consular Saldo \n4- Sair ");
                op = scan.nextInt();
                
                if (op == 1) {
                    System.out.println("Digite valor para depósito: ");
                    float valor = scan.nextFloat();
                    c = ref.depositoObj(c, valor);
                    System.out.println("Valor depositado " + valor + " ,saldo atual = " + c.getSaldo());

                }
                if (op == 2) {
                    System.out.println("Digite valor para saque: ");
                    float valor = scan.nextFloat();
                    c = ref.saqueObj(c, valor);
                }
                if (op == 3) {
                    System.out.println("Saldo: "+ref.saldoObj(c));
                }

            }

        } catch (NotBoundException ex) {
            System.out.println("Erro: provavelmente não tem nada associado a URL especificada");
        } catch (MalformedURLException ex) {
            System.out.println("Erro: URL mal formada");
        } catch (RemoteException ex) {
            System.out.println("Erro: Erro de comunicação");
        }
    }
}
