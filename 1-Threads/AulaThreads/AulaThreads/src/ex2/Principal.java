package ex2;


public class Principal {

    public static void main(String[] args) {
        
        //por padrão, todas as threads tem
        //a mesma prioridade, mas nós 
        //podemos modificar isso
        
        //cria um fluxo secundário
        MinhaThread t1 = new MinhaThread();
        t1.setPriority(1);//prioridade minima
        t1.start();
        
        //cria outro fluxo secundário
        MinhaThread t2 = new MinhaThread();
        t2.setPriority(10);//prioridade máxima
        t2.start();
        
        //cria outro fluxo secundário
        MinhaThread t3 = new MinhaThread();
        t3.setPriority(5);//prioridade normal
        t3.start();
        
        
    }
}
