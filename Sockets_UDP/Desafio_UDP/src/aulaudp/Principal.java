/*
Com base no estudo de caso da aplicação de desenho compartilhado desenvolvida durante a aula, realize mudanças de modo que:
a) Os clientes enviem para o servidor a própria imagem (BufferedImage) ao invés de enviar somente os pontos que estão desenhando;
b) O servidor, de alguma forma, precisará realizar um "merge" das imagens dos clientes...
c) Seja possível que um cliente, ao entrar no chat, receba a imagem que foi desenhada por clientes que já estavam no chat anteriormente;

Mensagens que o cliente recebe
a)Mensagem contendo uma lista de pontos
"PONTOS:xxx;yyy;rrr;ggg;bbb_xxx;yyy;rrr;ggg;bbb_xxx;yyy;rrr;ggg;bbb_xxx;yyy;rrr;ggg;bbb_..."
 */
package aulaudp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    Graphics2D g;
    DatagramSocket socket = null;

    ArrayList<String> imagem = new ArrayList<>();

    public Principal() {
        initComponents();
        BufferedImage img = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setBackground(Color.white);
        g.clearRect(0, 0, 400, 400);
        ImageIcon icon = new ImageIcon(img);
        jLblImagem.setIcon(icon);

        pedeAdicionar();
        criaThread();
    }

    public void enviaMensagem(String msg) {
        byte buffer[] = msg.getBytes();
        DatagramPacket pacote;
        try {
            pacote = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 1234);
            socket.send(pacote);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this, "Erro na criação do pacote");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro no envio do pacote");
        }
    }

    public void pedeAdicionar() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(this, "Erro na criação do socket");
        }
        enviaMensagem("ADICIONA");
    }

    //receber dados do servidor
    public void criaThread() {
        new Thread() {
            public void run() {
                while (true) {
                    byte buffer[] = new byte[50000];
                    DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                    try {
                        socket.receive(pacote);
                        buffer = pacote.getData();
                        String msg = new String(buffer, 0, pacote.getLength());
                        if (msg.startsWith("PONTOS")) {
                            String partes[] = msg.split(":");
                            String pontos[] = partes[1].split("_");
                            for (int i = 0; i < pontos.length - 1; i++) {
                                String pontoPartes[] = pontos[i].split(";");
                                int x = Integer.parseInt(pontoPartes[0]);
                                int y = Integer.parseInt(pontoPartes[1]);
                                int red = Integer.parseInt(pontoPartes[2]);
                                int green = Integer.parseInt(pontoPartes[3]);
                                int blue = Integer.parseInt(pontoPartes[4]);
                                Color cor = new Color(red, green, blue);
                                g.setColor(cor);
                                g.fillOval(x, y, 10, 10);
                            }
                            repaint();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro no recebimento de um pacote");
                    }
                }
            }
        }.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblImagem = new javax.swing.JLabel();
        jcc = new javax.swing.JColorChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLblImagem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLblImagemMouseDragged(evt);
            }
        });

        jcc.setColor(java.awt.Color.black);

        jButton1.setText("Enviar imagem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                .addComponent(jcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblImagem))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //envia os pontos ao clicar e arrastar o mouse
    private void jLblImagemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblImagemMouseDragged
        Color cor = jcc.getColor();
        g.setColor(cor);
        g.fillOval(evt.getX(), evt.getY(), 10, 10);
        repaint();

        String msg = "PONTO:" + evt.getX() + ";" + evt.getY() + ";" + cor.getRed() + ";" + cor.getGreen() + ";" + cor.getBlue();
        //adiciona cada ponto em uma lista e, ao clicar no botão enviar imagem, envia a lista contendo todos os pontos para o servidor
        imagem.add(msg);

        System.out.println(msg);
        //enviaMensagem(msg);
    }//GEN-LAST:event_jLblImagemMouseDragged

    //envia a lista para o servidor
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //percorrer a lista e envia para o servidor as coordenadas
        for (int i = 0; i < imagem.size(); i++) {
            enviaMensagem(imagem.get(i));
        }
        System.out.println("TAMANHO DA LISTA"+imagem.size());
    }//GEN-LAST:event_jButton1ActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLblImagem;
    private javax.swing.JColorChooser jcc;
    // End of variables declaration//GEN-END:variables
}
