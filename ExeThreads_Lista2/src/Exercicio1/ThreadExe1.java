
package Exercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadExe1 extends Thread{
    
    int sleep;
    
    public ThreadExe1(int sleep){
    this.sleep = sleep;
    
}
    @Override
    public void run(){
        
        while(Principal.podeExecutar()){        
                try {
                ThreadExe1.sleep(sleep);
                System.out.println("Thread "+getId()+" sleeping for "+sleep+" seconds...");
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadExe1.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        System.out.println("Thread "+getId()+" não tem permissão ");
    }   
}
