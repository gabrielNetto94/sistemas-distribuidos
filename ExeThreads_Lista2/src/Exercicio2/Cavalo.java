
package Exercicio2;

public class Cavalo extends Thread {
    int tamPercurso;
    int i =0;
    
    public Cavalo(int tamPercurso){
        this.tamPercurso = tamPercurso;
    }
    
    @Override
    public void run(){

        while(i < tamPercurso){
            i = i + Principal.velocidadeCavalo();      
        }
        if(i>=tamPercurso){
            Principal.testeGanhador((int) getId());
        }
    }
}
