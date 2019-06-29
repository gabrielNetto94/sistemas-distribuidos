package exercicio2;

import com.sun.glass.events.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Cliente_UI_2 extends javax.swing.JFrame {

    
    DatagramSocket socket;
    
    public Cliente_UI_2() {
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Cliente_UI_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        //iniciar thread para recebimento de mensagem
        tRecebeMensagem();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTFIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFPorta = new javax.swing.JTextField();
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
        jTFIP.setText("127.0.0.1");
        jTFIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIPActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Porta:");

        jTFPorta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFPorta.setText("1234");

        jTAChat.setEditable(false);
        jTAChat.setColumns(20);
        jTAChat.setRows(5);
        jScrollPane1.setViewportView(jTAChat);

        jTFMensagem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFMensagemKeyPressed(evt);
            }
        });

        jBTNEnviar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBTNEnviar.setText("Enviar");
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
                        .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 127, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jTFNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNEnviar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void tRecebeMensagem() {
        
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("flag");
                while (true) {
                    //cria pacote datagrama para receber a mensagem
                    DatagramPacket pacoteRec = new DatagramPacket(new byte[100], 100);

                    try {
                        //aguarda uma mensagem chegar pela rede no pacote
                        socket.receive(pacoteRec);//bloqueante
                        
                        //extrai os bytes do pacote recebido
                        byte bufferRec[] = pacoteRec.getData();
                        //converte os bytes pra String
                        String msgRec = new String(bufferRec);

                        //split de mensagem 
                        String msgSplit[] = msgRec.split("#");
                        System.out.println(msgSplit[0]);
                        System.out.println(msgSplit[1]);
                        
                        //mostra na tela a String recebida
                        System.out.println("Cliente recebeu: " + msgRec);
                        jTAChat.append(msgSplit[0]+" disse: "+msgSplit[1]+"\n");
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente_UI_2.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        t.start();
    }

    //ENVIAR MENSAGEM PARA O SERVIDOR
    public void enviaMensagem() throws UnknownHostException {

        try {
            String mensagem = jTFNick.getText()+'#'+jTFMensagem.getText();
//            byte bufferEnviar[] = jTFMensagem.getText().getBytes();
            byte bufferEnviar[] = mensagem.getBytes();
            //coloca os bytes dentro de um pacote datagrama; o endereçamento da mensagem para o servidor vai no próprio pacote
            DatagramPacket pacoteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, InetAddress.getByName("localhost"), 1234);
            //envia a mensagem
            socket.send(pacoteEnviar);
        } catch (IOException ex) {
            Logger.getLogger(Cliente_UI_2.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTFMensagem.setText("");
    }

    //BOTAO ENVIAR
    private void jBTNEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNEnviarActionPerformed
        try {
            enviaMensagem();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente_UI_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBTNEnviarActionPerformed

    private void jTFMensagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFMensagemKeyPressed
        //se apertar enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                enviaMensagem();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Cliente_UI_2.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            java.util.logging.Logger.getLogger(Cliente_UI_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente_UI_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente_UI_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente_UI_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente_UI_2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
