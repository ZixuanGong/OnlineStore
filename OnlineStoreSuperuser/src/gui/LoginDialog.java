package gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import utils.ServerInterface;

public class LoginDialog extends JDialog {
    ServerInterface server;
    
    public LoginDialog(ServerInterface server) {
        super();
        this.server = server;
        initComponents();
        
        //when user close the loginDialog, the whole app close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                idTb.requestFocus();
            }
            
        });
        
        setVisible(true);
    }
    public void setMessage(final String msg){
        SwingUtilities.invokeLater(new Runnable(){
        
        @Override
        public void run(){
            message.setText(msg) ;
        }
    }) ;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordTb = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        idTb = new javax.swing.JTextField();
        message = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roleCombo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(105, 29));

        jLabel1.setText("ID:");

        jLabel2.setText("Password:");

        passwordTb.setColumns(20);

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        idTb.setColumns(20);

        message.setText("Welcome!");
        message.setMaximumSize(new java.awt.Dimension(198, 15));
        message.setPreferredSize(new java.awt.Dimension(198, 15));

        jLabel3.setText("Login as: ");

        roleCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Accountant", "WH Keeper", "Manager" }));
        roleCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginBtn)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(roleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginBtn))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        
            String email = idTb.getText();
            String password = new String(passwordTb.getPassword());
            final String role = roleCombo.getSelectedItem().toString() ;
            
            setMessage("Logging in...");
            
            //validate if user has filled all the blanks
            if(email.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill the blanks!", "Invalid Operation", JOptionPane.ERROR_MESSAGE);
                setMessage("Welcome!");
            }
            else
            {
                try {
                    //validate user and send user email back to storeGUI
                    boolean loginValid = server.authenticateSuperuser(email, password);
                    
                    if(loginValid)
                    {
                        Thread thread = new Thread(new Runnable(){

                            @Override
                            public void run() {
                                //create a JFrame, and lauch specific panel
                                JFrame superuserGUI = new JFrame("Superuser");

                                switch(role){
                                    case "Accountant":
                                        AccountantPanel panel1 = new AccountantPanel(server);
                                        panel1.setOpaque(true);
                                        superuserGUI.setContentPane(panel1);

                                        break;
                                    case "WH Keeper":
                                        WHPanel panel2 = new WHPanel(server) ;
                                        panel2.setOpaque(true) ;
                                        superuserGUI.setContentPane(panel2);
                                        break;
                                        
                                    case "Manager":
                                        ManagerPanel panel3 = new ManagerPanel(server);
                                        panel3.setOpaque(true);
                                        superuserGUI.setContentPane(panel3);
                                        break;
                                }

                                superuserGUI.pack();
                                superuserGUI.setVisible(true);
                            }
                            
                        });
                        thread.start();                
                        
                        dispose() ;
                        
                    }
                    else
                        setMessage("Wrong login info, please try again! ") ;
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
                    setMessage("System error!");
                }
                    
            }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void roleComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleComboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idTb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel message;
    private javax.swing.JPasswordField passwordTb;
    private javax.swing.JComboBox roleCombo;
    // End of variables declaration//GEN-END:variables

    
    
    
}
