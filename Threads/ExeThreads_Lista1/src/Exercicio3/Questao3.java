//3. Crie uma classe chamada Questao3 cujo método main cria um objeto da própria classe Questao3. 
//O método construtor da classe Questao3 deverá declarar uma matriz de números inteiros de L linhas e C colunas, 
//sendo que estas dimensões são lidas pelo teclado.

//Em seguida, faça com que sejam criadas L threads, cada uma delas 
//responsável por realizar a soma dos elementos de uma linha da matriz. Por exemplo, a Thread 0 faz a soma dos elementos 
//da linha 0, a Thread 1 faz a soma dos elementos da linha 1, e assim por diante.
//*/

package Exercicio3;
import java.util.Random;
import java.util.Scanner;

public class Questao3  {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite o numero de Linhas:");
        int L = in.nextInt();
        
        System.out.println("Digite o numero de Colunas:");
        int C = in.nextInt();

        int m[][] = new int[L][C];
        
        populaMatriz(m,L,C);
        mostraMatriz(m,L,C);
        
        ThreadExe3 t[] = new ThreadExe3[L];
        
        for (int i = 0; i < L; i++) {
            t[i] = new ThreadExe3(m[i],i);
            t[i].start();
        }
        
    }
    public static void populaMatriz(int[][] m,int l, int c){
        Random gerador = new Random();
        
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < c; j++) {
                m[i][j] = gerador.nextInt(10);
            }
        }
    }
    
    public static void mostraMatriz(int[][] m,int l, int c){
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < c; j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}