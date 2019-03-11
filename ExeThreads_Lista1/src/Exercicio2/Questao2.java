/*
/*
2. Defina uma classe chamada Contador como uma subclasse de Thread, cujo método run() imprime números de 0 a 10 e:

Crie a classe Questao2 que deve definir o método main , este que cria e inicia a 
execução de uma thread da classe Contador. Teste o resultado executando a classe Questao2.

Agora altere o método main da classe Questao2 de modo que sejam criadas duas ou mais 
threads da classe Contador, e que em seguida inicialize a execução das mesmas.
*/

package Exercicio2;

public class Questao2 {
    
    public static void main(String[] args) {
        
        for(int i=0;i<2;i++){
            Contador c = new Contador();
            c.start();
        }
    }
}
