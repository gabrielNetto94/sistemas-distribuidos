
package Exercicio5;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadExe5 extends Thread{

    Random gerador = new Random();
    
    @Override
    public void run(){
        int sleep =  1000 * gerador.nextInt(6);   
        
        try {
            ThreadExe5.sleep(sleep);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadExe5.class.getName()).log(Level.SEVERE, null, ex);
        }        
        System.out.println("Thread: ["+getId()+"] Sleeping for "+sleep/1000+" seconds.");
    }    
}
