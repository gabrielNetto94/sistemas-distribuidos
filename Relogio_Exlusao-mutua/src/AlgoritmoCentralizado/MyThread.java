package AlgoritmoCentralizado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread {

    public void run() {
        try {
            Socket cliente = new Socket("localhost", 1234);

            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            DataInputStream in = new DataInputStream(cliente.getInputStream());

            int i;
            while (true) {
                try {
                    
                    
                    i = in.readInt();
                    System.out.println(cliente.getPort()+": "+i);
//                    Random r = new Random();
//                    sleep(r.nextInt(2001));
//                    
//                    //solicita permissão
//                    out.writeUTF("permissao");
//                    //recebe o retorno, se sim ou não
//                    boolean livre = in.readBoolean();
//
//                    if (livre) {
//                        System.out.println("thread permissão");
//                        sleep(2000);
//                        out.writeUTF("libera");
//                    }
//                    if(!livre){
//                        sleep(2000);
//                        out.writeUTF("permissao");
//                    }

                } catch (IOException ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                } 
//                catch (InterruptedException ex) {
//                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
//                }

            }
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
