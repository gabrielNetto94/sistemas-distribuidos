package ex2;

public class MinhaThread extends Thread{
    
    public void run(){
        int cont = 0;
        while(true){
            cont++;
            System.out.println(getId()+":"+cont);
        }
    }
}
