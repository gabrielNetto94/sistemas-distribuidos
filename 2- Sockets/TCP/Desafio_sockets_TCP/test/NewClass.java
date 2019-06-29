
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class NewClass {

    public static void main(String[] args) {
        //String oi = "origem;destino;minha mensagem kjaks aisuheiuas eiua seiua heiua hs";
//        String oi = "#1";
//        String[] s = oi.split("#");
//
//        //System.out.println("PVT " +s[0]+" para -> " + s[1] + ": " + oi.substring(s[0].length()+1, oi.length()));
//        System.out.println("s[1] =  " + s[1]);
//
//        String frase = JOptionPane.showInputDialog(null, "Digite uma frase: ");
//        String fraseInvertida = new StringBuilder(frase).reverse().toString();
//        System.out.println(fraseInvertida);
        Scanner teclado = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int senha1;
        int senha2;

        System.out.println("Digite uma senha para sua conta:");
        senha1 = teclado.nextInt();
        System.out.println("Repita a senha:");
        senha2 = teclado.nextInt();

        while (senha1 != senha2) {
            System.out.println("Senha não coincidem!");
            teclado.nextLine();
            System.out.println("Digite uma senha para sua conta:");
            senha1 = teclado.nextInt();
            System.out.println("Repita a senha:");
            senha2 = teclado.nextInt();
        }
    }

}
