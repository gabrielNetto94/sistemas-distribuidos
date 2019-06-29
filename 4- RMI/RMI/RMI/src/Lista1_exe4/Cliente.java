package Lista1_exe4;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            ArrayList<String> listaArquivo = new ArrayList<>();

            int op = 0;

            IMetodosServidor server = (IMetodosServidor) Naming.lookup("rmi://localhost/MetodosServidor");

            String path = "C:/saida.txt";
            String texto = "Hello World!";

            while (op != 5) {
                Scanner scan = new Scanner(System.in);

                System.out.println(""
                        + "1 - Ler arquivo inteiro \n"
                        + "2 - Ler linha específica \n"
                        + "3 - Escrever no arquivo \n"
                        + "4 - Excluir arquivo  \n"
                        + "5 - Sair do programa");

                System.out.print("Digite Opção: ");
                op = scan.nextInt();
                switch (op) {
                    case 1:
                        listaArquivo = server.arquivoLer(path);
                        for (String l : listaArquivo) {
                            System.out.println(l);
                        }
                        break;
                    case 2:
                        System.out.print("Digite a linha que desejar ler: ");
                        int linha = scan.nextInt();

                        String linhaLida = server.arquivoLerLinha(path, linha);

                        if (linhaLida.equals("fail")) {
                            System.out.println("Linha não existe no arquivo");
                        } else {
                            System.out.println(linhaLida);
                        }

                        break;
                    case 3:

                        System.out.print("Escreve no arquivo: ");
                        String msg = scan.nextLine();
                        msg = scan.nextLine();
                        server.arquivoEscreve(path, msg);
                        break;

                    case 4:
                        server.arquivoDeletar(path);
                        System.out.println("Arquivo no diretório: " + path + "  deletado!");
                        break;

                    default:
                        System.out.println("Opção inválida! ");
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
