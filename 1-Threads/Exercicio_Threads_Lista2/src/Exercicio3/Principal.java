/*
 Faça uma aplicação em que a thread principal cria N threads, cujo construtor da classe thread 
 recebe um identificador (de 0 a N-1). Cada thread criada deverá enviar uma mensagem a thread 
 com identificador +1, exceto a thread com identificador N-1 que enviará uma mensagem a thread 
 de identificador 0. As threads só podem terminar sua execução no momento em que receberem a sua 
 mensagem. Resumindo, implementar uma comunicação em anel entre as threads (sem envolver a thread principal).
 */
package Exercicio3;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        int numThreads;
        numThreads = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero a serem criadas: "));

        MyThread t[] = new MyThread[numThreads];

        //instancia as threads
        for (int i = 0; i < numThreads; i++) {
            t[i] = new MyThread(i);
        }

        //conecta as threads
        for (int i = 0; i < numThreads; i++) {
            if (i == (numThreads - 1)) {
                t[i].ProxThread = t[0];
            } else {
                t[i].ProxThread = t[i + 1];
            }
        }

        //iniciar as threads
        for (int i = 0; i < numThreads; i++) {
            t[i].start();
        }
    }
}
