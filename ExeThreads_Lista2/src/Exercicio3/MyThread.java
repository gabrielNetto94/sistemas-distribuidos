
package Exercicio3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread{    
    int id;
    private final Object Thread;
    
    public MyThread(Object Thread){
        
        this.Thread = Thread;
    }
    @Override
    public void run(){
        try {
            Thread.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
