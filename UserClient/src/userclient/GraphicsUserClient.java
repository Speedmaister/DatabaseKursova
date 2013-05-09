package userclient;

import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class GraphicsUserClient extends javax.swing.JFrame {

    private UserClientThread thread;

    public GraphicsUserClient(UserClientThread thread) {
        this.thread = thread;
    }

    public void invokeGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
            }
        });
        this.setVisible(true);
        this.isValid();
    }

    public void writeToTextPanel(String text) {
        this.MessageViewer.setText("");
        this.MessageViewer.append(text);
    }

    private String getUsername() {
        return this.UserNameTextField.getText();
    }

    private String getPassword() {
        return this.PasswordTextField.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TwitterMainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MessageViewer = new javax.swing.JTextArea();
        MessageTextField = new javax.swing.JTextField();
        SendButton = new javax.swing.JButton();
        SubscribeToChannelTextField = new javax.swing.JTextField();
        SubscribeToChannelButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        UserDataPanel = new javax.swing.JPanel();
        UserNameLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        UserNameTextField = new javax.swing.JTextField();
        PasswordTextField = new javax.swing.JTextField();
        LoginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TwitterMainPanel.setBackground(new java.awt.Color(51, 0, 255));

        MessageViewer.setColumns(20);
        MessageViewer.setRows(5);
        jScrollPane1.setViewportView(MessageViewer);

        MessageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MessageTextFieldKeyPressed(evt);
            }
        });

        SendButton.setText("Send");
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        SubscribeToChannelButton.setText("Subscribe");
        SubscribeToChannelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubscribeToChannelButtonActionPerformed(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TwitterMainPanelLayout = new javax.swing.GroupLayout(TwitterMainPanel);
        TwitterMainPanel.setLayout(TwitterMainPanelLayout);
        TwitterMainPanelLayout.setHorizontalGroup(
            TwitterMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TwitterMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TwitterMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(TwitterMainPanelLayout.createSequentialGroup()
                        .addComponent(MessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(SendButton))
                    .addGroup(TwitterMainPanelLayout.createSequentialGroup()
                        .addComponent(SubscribeToChannelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SubscribeToChannelButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TwitterMainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ExitButton)))
                .addContainerGap())
        );
        TwitterMainPanelLayout.setVerticalGroup(
            TwitterMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TwitterMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TwitterMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SendButton))
                .addGap(18, 18, 18)
                .addGroup(TwitterMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubscribeToChannelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubscribeToChannelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(ExitButton)
                .addContainerGap())
        );

        UserDataPanel.setBackground(new java.awt.Color(255, 153, 0));

        UserNameLabel.setText("Username :");

        PasswordLabel.setText("Password :");

        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserDataPanelLayout = new javax.swing.GroupLayout(UserDataPanel);
        UserDataPanel.setLayout(UserDataPanelLayout);
        UserDataPanelLayout.setHorizontalGroup(
            UserDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UserDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UserDataPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LoginButton)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(UserDataPanelLayout.createSequentialGroup()
                        .addGroup(UserDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PasswordTextField)
                            .addGroup(UserDataPanelLayout.createSequentialGroup()
                                .addGroup(UserDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UserNameLabel)
                                    .addComponent(PasswordLabel))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(UserNameTextField))
                        .addContainerGap())))
        );
        UserDataPanelLayout.setVerticalGroup(
            UserDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(LoginButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TwitterMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(UserDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TwitterMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed
        String text = this.MessageTextField.getText();
        this.MessageTextField.setText("");
        this.thread.sendMessage("message");
        this.thread.sendMessage(text);
    }//GEN-LAST:event_SendButtonActionPerformed

    private void SubscribeToChannelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubscribeToChannelButtonActionPerformed
        String channelOwner = this.SubscribeToChannelTextField.getText();
        this.SubscribeToChannelTextField.removeAll();
        this.thread.sendMessage("subscribe");
        this.thread.sendMessage(channelOwner);
    }//GEN-LAST:event_SubscribeToChannelButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        try {
            this.thread.close();
            this.dispose();
        } catch (IOException ex) {
            System.out.println("Closing client.");
        }
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        try {
            DataOutputStream out = this.thread.getOutputStream();
            String userName = this.getUsername();
            String password = this.getPassword();
            out.writeUTF(userName);
            out.writeUTF(password);
            this.UserDataPanel.setVisible(false);
        } catch (IOException ex) {
            System.out.println("Error occurred while trying to login to server.");
        }
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void MessageTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MessageTextFieldKeyPressed

    }//GEN-LAST:event_MessageTextFieldKeyPressed

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
            java.util.logging.Logger.getLogger(GraphicsUserClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicsUserClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicsUserClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicsUserClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton LoginButton;
    private javax.swing.JTextField MessageTextField;
    private javax.swing.JTextArea MessageViewer;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JTextField PasswordTextField;
    private javax.swing.JButton SendButton;
    private javax.swing.JButton SubscribeToChannelButton;
    private javax.swing.JTextField SubscribeToChannelTextField;
    private javax.swing.JPanel TwitterMainPanel;
    private javax.swing.JPanel UserDataPanel;
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JTextField UserNameTextField;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
