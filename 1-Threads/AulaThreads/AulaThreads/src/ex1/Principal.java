package ex1;


public class Principal {

    //fluxo de execução principal
    public static void main(String[] args) {

        //criação de uma thread
        MinhaThread t1 = new MinhaThread();
        //execução de uma thread
        t1.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello World da principal");
        }
    }

}
