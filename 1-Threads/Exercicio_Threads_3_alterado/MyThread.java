
package Exercicio3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread{    
    
    MyThread ProxThread;

    @Override
    public void run(){
            System.out.println("Meu id: "+getId());
        try {
            wait();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
