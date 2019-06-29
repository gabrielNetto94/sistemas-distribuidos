package ex1;

//Para especificar uma thread, a classe precisa herdar
//a classe Thread do java

public class MinhaThread extends Thread {

    //O método run é o método que define o que a thread
    //irá executar, e é obrigatório em qualquer thread
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello World da thread");
        }
    }
}
