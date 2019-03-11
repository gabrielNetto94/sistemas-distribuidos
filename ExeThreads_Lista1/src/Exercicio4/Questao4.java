package Exercicio4;
import java.util.Random;
import java.util.Scanner;

public class Questao4 {

    public static void main(String[] args) throws InterruptedException {
        Random gerador = new Random();
        int n;
        int sleep;
        
        Scanner in = new Scanner(System.in);

        System.out.println("Digite quantidade de Threads: ");
        int numThreads = in.nextInt();
        
        ThreadExe4 v[] = new ThreadExe4[numThreads];
        
        for (int i=0; i<numThreads; i++) {
            n = gerador.nextInt(100000);
            sleep =  1000 * gerador.nextInt(6);
            v[i] = new ThreadExe4(n,sleep);
            v[i].start();
        }
    }
}
