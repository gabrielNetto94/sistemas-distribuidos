package Exercicio3;

class ThreadExe3 extends Thread{

    int[] vet;
    int L;
    
    public ThreadExe3(int[] vet, int L) {
       this.vet = vet;
       this.L = L;
    }
    
    @Override
    public void run() {
        int somatorio = 0;

        for ( int i = 0; i < vet.length; i++) {
            somatorio += vet[i];
        }   
        System.out.println("Soma da linha "+L+" = " + somatorio);    
    }
}