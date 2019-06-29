/*Faça uma aplicação em que a thread principal (main()) crie algumas threads, e que de tempos
em tempos as threads secundárias peçam “permissão” para a thread principal para continuar 
executando. Caso a thread principal de a permissão, elas continuam executando por um tempo 
aleatório, caso contrário, devem parar totalmente. 
A permissão dada (ou não) pela thread principaldeve ser implementada de
maneira aleatória, ou seja, a thread principal decide aleatoriamente 
se vai dar ou não permissão para que uma thread secundária continue.*/

package Exercicio1;

import java.util.Random;

public class Principal {
    
    public static Random r = new Random();
    
    public static void main(String[] args) {
        
        int num = r.nextInt(20);

        for(int i=0;i< num ;i++){
            ThreadExe1 t = new ThreadExe1(1000*r.nextInt(6));
            t.start();
        }
    }
    public static boolean podeExecutar(){
        return r.nextBoolean();
    }
}