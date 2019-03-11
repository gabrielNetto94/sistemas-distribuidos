/*
Faça uma aplicação Java com threads que simule uma corrida de cavalos, em que 
cada cavalo é uma thread. Os cavalos devem percorrer alguma quantidade de metros 
na corrida (por exemplo, 500 metros), e cada cavalo tem uma velocidade média de 
16m/s, podendo variar para mais e para menos (variação esta que deve ser calculada 
de forma aleatória). Faça uma aplicação java em que o usuário insere o número de cavalos
da corrida e o a quantidade de metros da corrida, e inicie uma thread para cada 
cavalo, que a cada interação anda alguns metros (dependendo da sua velocidade). Em seguida, informe qual cavalo foi o campeão.
*/

package Exercicio2;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Principal {
    
    public static int cont = 0;
    
    public static Scanner s = new Scanner(System.in);
    public static Random r =  new Random();
    
    public static void main(String[] args) {
        
        int qtdCavalos;
        int tamPercurso;
        
        qtdCavalos = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero de cavalos da corrida: "));
        tamPercurso = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o tamanho do percurso em metros: "));
        
        for (int i = 0; i < qtdCavalos; i++) {
            Cavalo c =  new Cavalo(tamPercurso);
            c.start();
        }
    }
    public static synchronized int velocidadeCavalo(){
        int num = r.nextInt(50);
        if(r.nextBoolean() == true){
            return num + 16 ;
        }else{
            return num * -1 + 16;
        }
    }
    
    public static synchronized void testeGanhador(int idCavalo){
        cont++;
        System.out.println("Cavalo "+ idCavalo+ " em "+cont+"º lugar!" );
    }
}
