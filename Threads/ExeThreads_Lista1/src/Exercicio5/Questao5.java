/*
 5.Escreva um programa em que a thread principal (main) deverá ter um loop que a cada ciclo deverá criar
uma nova thread, no máximo de 10 threads. 
Cada thread criada pela main deverá, no método run(),
simplesmente suspender sua execução por um tempo aleatório entre 0 e 5 segundos e encerrar.

A main deve manter sempre 10 threads em execução, ou seja, nunca acima nem abaixo deste número.
Quando alguma thread secundária encerrar sua execução, a main deverá descobrir isso de alguma 
forma e então criar uma nova thread para manter sempre 10 threads em execução.
*/
package Exercicio5;

public class Questao5 extends Thread{
    public static void main(String[] args) throws InterruptedException {
        
        ThreadExe5 t = new ThreadExe5();
        while(true){
            while(ThreadExe5.activeCount() < 10){

                t = new ThreadExe5();
                t.start();            
                System.out.println("Thread ativas: "+ThreadExe5.activeCount());    
            }
        }
    }    
}

