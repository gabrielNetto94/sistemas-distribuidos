package ex4;

import javax.swing.JLabel;

public class MinhaThread extends Thread {

    JLabel label;
    
    public void run() {
        int cont = 0;
        while (true) {
            cont++;
            label.setText(getId() + ":" + cont + "");
        }
    }
}
