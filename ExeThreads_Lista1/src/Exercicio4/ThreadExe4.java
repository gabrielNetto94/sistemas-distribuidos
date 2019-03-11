package Exercicio4;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadExe4 extends Thread{
    
    int n;
    int sleep;
    
    public ThreadExe4(int n,int sleep){
        this.n = n;
        this.sleep = sleep;
    }

    @Override
    public void run(){
        System.out.println("Thead "+getId()+" criada");
        
        for (int i = 0; i <= n; i++) {
            System.out.println("Thead id: "+getId()+" "+i);
        }
        System.out.println("Thread id:"+getId()+" entrando em espera");
        try {
            ThreadExe4.sleep(sleep);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadExe4.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Thread id:"+getId()+" saindo da espera");
        System.out.println("Thread id:"+getId()+" Finalizada");
    }
}
