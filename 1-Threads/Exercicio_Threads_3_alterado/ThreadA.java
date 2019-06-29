/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercicio3;

public class ThreadA {
  
       public static void main(String[] args){
        ThreadB b = new ThreadB();
        b.start();
  
        synchronized(b){
            try{
                System.out.println("Aguardando o b completar...");
                b.wait();
            }catch(InterruptedException e){
            
            }
  
            System.out.println("Total é igual a: " + b.total);
        }
    }
}
class ThreadB extends Thread {
  
          int total;
           @Override
           public void run(){
               synchronized(this){
                   for(int i=0; i<100 ; i++){
                       total += i;
                   }
                   notify();
               }
           }
 }
