package Lista1_exe4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetodosServidor extends UnicastRemoteObject implements IMetodosServidor {

    public MetodosServidor() throws RemoteException {

    }

    @Override
    public ArrayList arquivoLer(String path) throws RemoteException {
        try {
            ArrayList<String> listaArquivo = new ArrayList<>();

            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha = null;

            while (buffRead.ready()) {
                linha = buffRead.readLine();
                listaArquivo.add(linha);
                System.out.println(linha);

            }
            buffRead.close();
            return listaArquivo;

        } catch (IOException ex) {
            Logger.getLogger(MetodosServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public String arquivoLerLinha(String path, int numeroLinha) throws RemoteException {
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha;

            int countLinha = 0;

            while (buffRead.ready()) {
                linha = buffRead.readLine();
                countLinha++;
            }
            buffRead.close();

            // "reseta" o buffRead para ele ler novamente a primeira linha
            buffRead = new BufferedReader(new FileReader(path));

            //se a linha que o usuario deseja não existe retorna "fail"
            if (countLinha < numeroLinha) {
                return "fail";
            } else {
                //se existe lê a linha e retorna
                countLinha = 0;
                while (buffRead.ready()) {
                    linha = buffRead.readLine();
                    countLinha++;

                    if (countLinha == numeroLinha) {
                        return linha;
                    }
                }
            }
            buffRead.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MetodosServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "fail";
    }

    @Override
    public void arquivoEscreve(String path, String texto) throws RemoteException {
        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));
            buffWrite.append(texto + "\n");
            buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(MetodosServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void arquivoDeletar(String path) throws RemoteException {
        File file = new File(path);
        file.delete();
    }

}
