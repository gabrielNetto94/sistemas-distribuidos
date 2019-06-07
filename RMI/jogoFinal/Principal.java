package jogoFinal;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    String ESPACO = " ";
    int LIN = 15, COL = 35;

    int matriz[][];

    int meu_codigo, minha_linha, minha_coluna;

    IServidor ref;
    Cliente cli;

    public int moeda, linha, coluna;

    class Cliente extends UnicastRemoteObject implements ICliente {

        Cliente() throws RemoteException {

        }

        @Override
        public void recebePosicao(int linha, int coluna, int codigo) throws RemoteException {
            System.out.println("Cliente recebeu atualização: " + codigo + ", " + linha + ", " + coluna);
            //retira o codigo da matriz
            for (int i = 0; i < LIN; i++) {
                for (int j = 0; j < COL; j++) {
                    if (matriz[i][j] == codigo) {
                        matriz[i][j] = 0;
                    }
                }
            }
            //atualiza a matriz com a nova posição do cliente
            matriz[linha][coluna] = codigo;

            atualizaTextArea();
        }

        @Override
        public void liberaPosicao(int codigo) throws RemoteException {
            for (int i = 0; i < LIN; i++) {
                for (int j = 0; j < COL; j++) {
                    if (matriz[i][j] == codigo) {
                        matriz[i][j] = 0;
                    }
                }
            }
            atualizaTextArea();
        }

        @Override
        public void atualizaJogadores(int numJogadores) throws RemoteException {
            jLNumero.setText(" ");
            jLNumero.setText(Integer.toString(numJogadores));
        }

        @Override
        public void updateTextArea(int codigo) throws RemoteException {
            liberaPosicao(codigo);
        }

    }

    public Principal() {
        initComponents();
        matriz = new int[LIN][COL];
        limpaMatriz();//zera a matriz
        if (!registraNoServidor()) {//registra no servidor e obtem um código
            JOptionPane.showMessageDialog(null, "Não foi possível logar! \nMotivo: número máximo de jogadores são 10");
            System.exit(0);
        } else {
            geraPosicaoInicial(); //gera uma posição inicial aleatória para o cliente
            atualizaTextArea();//atualiza o textarea com os valores da matriz
        }

    }

    public boolean registraNoServidor() {
        try {
            ref = (IServidor) Naming.lookup("rmi://localhost/Servidor");
            cli = new Cliente();
            meu_codigo = ref.registraCliente(cli);

            //se retornar -1 não deve deixar o cliente jogar 
            if (meu_codigo == -1) {
                return false;
            }
            System.out.println("Meu código é " + meu_codigo);
        } catch (NotBoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public void geraPosicaoInicial() {

        try {
            do {
                Random r = new Random();
                minha_linha = r.nextInt(LIN);
                minha_coluna = r.nextInt(COL);

                System.out.println("C: " + meu_codigo + " L: " + minha_linha + " C: " + minha_coluna);

            } while (!ref.posicaoValida(minha_linha, minha_coluna));

            System.out.println("Posição validada, enviando posição para o servidor!");

            ref.enviaPosicao(minha_linha, minha_coluna, meu_codigo);

        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void limpaMatriz() {
        for (int i = 0; i < LIN; i++) {
            for (int j = 0; j < COL; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    void atualizaTextArea() {
        jTAJogo.setText("");
        for (int i = 0; i < LIN; i++) {
            for (int j = 0; j < COL; j++) {
                switch (matriz[i][j]) {
                    case 0:
                        jTAJogo.append(ESPACO);
                        break;
                    case 11:
                        jTAJogo.append("@ ");
                        break;
                    default:
                        jTAJogo.append(matriz[i][j] + "");
                        break;
                }
            }
            jTAJogo.append("\n");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTAJogo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLNumero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTAJogo.setEditable(false);
        jTAJogo.setColumns(20);
        jTAJogo.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTAJogo.setRows(5);
        jTAJogo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTAJogoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTAJogo);

        jLabel1.setText("Jogadores Online: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviaPosicao(int linha, int coluna, int codigo) {
        try {
            ref.enviaPosicao(linha, coluna, codigo);
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void jTAJogoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAJogoKeyPressed
        switch (evt.getKeyCode()) {
            case VK_RIGHT:
                if (minha_coluna + 1 < COL) {
                    minha_coluna++;
                    enviaPosicao(minha_linha, minha_coluna, meu_codigo);
                }
                break;
            case VK_LEFT:
                if (minha_coluna - 1 >= 0) {
                    minha_coluna--;
                    enviaPosicao(minha_linha, minha_coluna, meu_codigo);
                }
                break;
            case VK_UP:
                if (minha_linha - 1 >= 0) {
                    minha_linha--;
                    enviaPosicao(minha_linha, minha_coluna, meu_codigo);
                }
                break;
            case VK_DOWN:
                if (minha_linha + 1 < LIN) {
                    minha_linha++;
                    enviaPosicao(minha_linha, minha_coluna, meu_codigo);
                }
                break;
            default:
                break;
        }

        System.out.println(minha_linha + ", " + minha_coluna);
    }//GEN-LAST:event_jTAJogoKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            ref.meTiraDaLista(meu_codigo, cli);
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ao fechar o game, mandar o servidor atulizar o textArea dos clientes
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //AO FECHAR A JANEA REMOVA O JOGADOR DA LISTA DE ONLINE
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAJogo;
    // End of variables declaration//GEN-END:variables
}
