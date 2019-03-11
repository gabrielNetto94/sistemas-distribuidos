
/*
1. Crie uma classe que estenda a classe Thread chamada ThreadMostraNome cujo método construtor desta 
classe receba uma String como parâmetro, e então imprima esta String na tela indefinidamente no seu 
método run(). Em seguida, crie uma nova classe chamada Questao1 que implemente o método main, este 
responsável por criar dez  threads da classe ThreadMostraNome e colocá-las para execução. 
*/
package exercicio1;

public class ThreadMostraNome extends Thread{

    String msg;
    public ThreadMostraNome(String str){
        msg = str;
    }
    
      public void run(){
         while(true){
             System.out.println(msg+"");
        }
      }
}
