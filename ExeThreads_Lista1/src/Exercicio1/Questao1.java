/*
1. Crie uma classe que estenda a classe Thread chamada ThreadMostraNome cujo método construtor desta 
classe receba uma String como parâmetro, e então imprima esta String na tela indefinidamente no seu 
método run(). Em seguida, crie uma nova classe chamada Questao1 que implemente o método main, este 
responsável por criar dez  threads da classe ThreadMostraNome e colocá-las para execução. 
*/
package exercicio1;

public class Questao1 {
    public static void main(String[] args) {
        

        ThreadMostraNome v[] = new ThreadMostraNome[10];
        
        for (int i=0; i<10; i++) {
            String nome = "Thread: "+i;
            v[i] = new ThreadMostraNome(nome);
            v[i].start();
        }
        
    }
}
