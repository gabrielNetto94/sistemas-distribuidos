package Lista2_chatRmi;

import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente_UI extends javax.swing.JFrame {
    public String[] split;
    
    
    public class MetodosCliente extends UnicastRemoteObject implements IMetodosCliente {

        public MetodosCliente() throws RemoteException {

        }

        //método que o servidor invocará no cliente para passar o horário atual do sistema
        @Override
        public void atualizaCliente(String mensagem) throws RemoteException {
            split = mensagem.split("@");
            System.out.println(split[0] + " enviou: " + split[1]);
            AtualizaFrame();
        }

        @Override
        public void recebeMensagem(String mensagem) throws RemoteException {

        }

        @Override
        public void atualizaClienteConectados(String mensagem) throws RemoteException {
            //chama metodo para atualizar no frame
            atualizarListaConectados(mensagem);
        }

    }

    public void AtualizaFrame() {
        jTAChat.append(split[0] + " enviou: " + split[1]+"\n");
    }
    public void atualizarListaConectados(String mensagem){
        jTAclientes.append(mensagem+"\n");
        
        
    }
    public Cliente_UI() {

        initComponents();
        this.setLocationRelativeTo(null);

    }
    String ip;
    String nick;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTFIP = new javax.swing.JTextField();
        jBTNConectar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAChat = new javax.swing.JTextArea();
        jTFMensagem = new javax.swing.JTextField();
        jBTNEnviar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTFNick = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAclientes = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

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
        jTFMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFMensagemActionPerformed(evt);
            }
        });
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
        jTFNick.setText("nickName");
        jTFNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNickActionPerformed(evt);
            }
        });

        jTAclientes.setEditable(false);
        jTAclientes.setColumns(20);
        jTAclientes.setRows(5);
        jScrollPane2.setViewportView(jTAclientes);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Clientes conectados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTFMensagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBTNEnviar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFNick, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(jBTNConectar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(26, 26, 26)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBTNConectar)
                    .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jTFNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNEnviar))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNConectarActionPerformed

        ip = jTFIP.getText();
        nick = jTFNick.getText();

        try {
            IMetodosServidor ref = (IMetodosServidor) Naming.lookup("rmi://localhost/MetodosServidor");

            MetodosCliente cli = new MetodosCliente();
            ref.registraCliente(cli,nick);

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Cliente_UI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBTNConectarActionPerformed

    public void enviaMensagem() {
        try {
            IMetodosServidor ref = (IMetodosServidor) Naming.lookup("rmi://localhost/MetodosServidor");
            MetodosCliente cli = new MetodosCliente();

            ref.recebeMensagem(nick + "@" + jTFMensagem.getText());

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Cliente_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void jTFNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNickActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNickActionPerformed

    private void jTFMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFMensagemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFMensagemActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente_UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTNConectar;
    private javax.swing.JButton jBTNEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAChat;
    private javax.swing.JTextArea jTAclientes;
    private javax.swing.JTextField jTFIP;
    private javax.swing.JTextField jTFMensagem;
    private javax.swing.JTextField jTFNick;
    // End of variables declaration//GEN-END:variables
}
