package gui;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
//import static java.time.Clock.system;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import store.StoreGUI;
import utils.Customer;
/*
** To be continued: validate email input format
*/

public class LoginDialog extends JDialog {
    private StoreGUI store;
    
    public LoginDialog(Frame frame, StoreGUI store) {
        super(frame,true);
        this.store = store;
        initComponents();
        
        //when user close the loginDialog, the whole app close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                emailTb.requestFocus();
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
        jButton2 = new javax.swing.JButton();
        emailTb = new javax.swing.JTextField();
        message = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");

        jLabel1.setText("Email:");

        jLabel2.setText("Password:");

        passwordTb.setColumns(20);

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        jButton2.setText("Register");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        emailTb.setColumns(20);

        message.setText("Welcome!");
        message.setMaximumSize(new java.awt.Dimension(198, 15));
        message.setPreferredSize(new java.awt.Dimension(198, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(emailTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBtn)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        
            String email = emailTb.getText();
            String password = new String(passwordTb.getPassword());
            setMessage("Logging in...");
            
            //validate if user has filled all the blanks
            if(email.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill the blanks!", "Invalid Operation", JOptionPane.ERROR_MESSAGE);
                setMessage("Welcome!");
            }
            else{
                
                try{
                    //validate user and send user email back to storeGUI
                    int loginValid = Customer.login(email, password);
                    
                    switch(loginValid){
                        case Customer.LOGIN_VALID:
                            store.setCustomer(email);
                            dispose();
                            break;
                        case Customer.NO_SUCH_ROW:
                            setMessage("No such account, please register!");
                            break;
                        case Customer.WRONG_PW:
                            setMessage("Invalid Email or password!");
                            break;
                    }
                } catch(RemoteException e){
                    setMessage("Error in connection!");
                } 
            }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new RegisterDialog(LoginDialog.this) ;
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailTb;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel message;
    private javax.swing.JPasswordField passwordTb;
    // End of variables declaration//GEN-END:variables

    
    
    
}
