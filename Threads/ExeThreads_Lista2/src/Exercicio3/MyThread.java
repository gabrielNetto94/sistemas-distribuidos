package Exercicio3;
import java.util.Random;

public class MyThread extends Thread {

    int id;
    
    public MyThread(int id) {
        this.id = id;
    }
    
    MyThread ProxThread;

    @Override
    public void run() {
        System.out.println("Meu id: " + id + " Id ProxThread: " + ProxThread.id);
        
        while (ProxThread.bool() == false) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                
            }   
        }       
    }

    public static synchronized boolean bool() {
        Random r = new Random();
        return r.nextBoolean();
    }
}
