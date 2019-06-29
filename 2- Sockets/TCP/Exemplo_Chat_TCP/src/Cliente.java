
import com.sun.glass.events.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author usrlab01
 */
public class Cliente extends javax.swing.JFrame {

    Socket cliente;

    public Cliente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTFIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFPorta = new javax.swing.JTextField();
        jBTNConectar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAChat = new javax.swing.JTextArea();
        jTFMensagem = new javax.swing.JTextField();
        jBTNEnviar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTFNick = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("IP:");

        jTFIP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFIP.setText("172.25.0.154");
        jTFIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIPActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Porta:");

        jTFPorta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFPorta.setText("1234");

        jBTNConectar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBTNConectar.setText("Conectar");
        jBTNConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNConectarActionPerformed(evt);
            }
        });

        jTAChat.setEditable(false);
        jTAChat.setColumns(20);
        jTAChat.setRows(5);
        jScrollPane1.setViewportView(jTAChat);

        jTFMensagem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFMensagem.setEnabled(false);
        jTFMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFMensagemKeyPressed(evt);
            }
        });

        jBTNEnviar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBTNEnviar.setText("Enviar");
        jBTNEnviar.setEnabled(false);
        jBTNEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNEnviarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Nick:");

        jTFNick.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFNick.setText("cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTFMensagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBTNEnviar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNick, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBTNConectar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBTNConectar)
                    .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jTFNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNEnviar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNConectarActionPerformed
        String ip = jTFIP.getText();
        int porta = Integer.parseInt(jTFPorta.getText());
        try {
            //cria o socket do cliente, se conectando ao servidor
            cliente = new Socket(ip, porta);

            //habilita os campos de envio de mensagem
            jBTNEnviar.setEnabled(true);
            jTFMensagem.setEnabled(true);

            //desabilita o botão de conectar
            jBTNConectar.setEnabled(false);

            jTAChat.append("Conexão realizada com sucesso no ip " + ip + ":" + porta + "\n");

            //cria uma thread para esperar mensagens do servidor
            Thread t = new Thread() {
                public void run() {
                    try {
                        DataInputStream in = new DataInputStream(cliente.getInputStream());
                        while (true) {
                            //espera uma string do servidor
                            String mensagem = in.readUTF();
                            jTAChat.append(mensagem + "\n");
                        }
                    } catch (IOException ex) {
                        System.out.println("Erro ao extrair o input stream");
                    }

                }
            };
            t.start();
        } catch (IOException ex) {
            System.out.println("Erro ao conectar o cliente");
        }
    }//GEN-LAST:event_jBTNConectarActionPerformed

    public void enviaMensagem() {
        try {
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            String mensagem = jTFNick.getText() + " disse: " + jTFMensagem.getText();
            out.writeUTF(mensagem);
        } catch (IOException ex) {
            System.out.println("Erro ao extrair o output stream");
        }
        jTFMensagem.setText("");
    }

    private void jBTNEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNEnviarActionPerformed
        enviaMensagem();
    }//GEN-LAST:event_jBTNEnviarActionPerformed

    private void jTFMensagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFMensagemKeyPressed
        //se apertar enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            enviaMensagem();
        }
    }//GEN-LAST:event_jTFMensagemKeyPressed

    private void jTFIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIPActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTNConectar;
    private javax.swing.JButton jBTNEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAChat;
    private javax.swing.JTextField jTFIP;
    private javax.swing.JTextField jTFMensagem;
    private javax.swing.JTextField jTFNick;
    private javax.swing.JTextField jTFPorta;
    // End of variables declaration//GEN-END:variables
}
