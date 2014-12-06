package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import utils.Order;

/*this panel set the order's delivery_details, and saves the order to db
 * 
 * 
 */

public class DeliveryPanel extends javax.swing.JPanel {
    private OrderDialog orderDlg;
    private JPanel allPanel;
    private Order order;

    public DeliveryPanel(OrderDialog dialog) {
        
        orderDlg = dialog;
        this.order = orderDlg.getOrder();
        this.allPanel = orderDlg.getAllPanel();
        
        initComponents();
        
    }
    
    private void setMessage(final String msg){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                message.setText(msg);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addressTf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        zipTf = new javax.swing.JTextField();
        ConfirmBtn = new javax.swing.JButton();
        PreviousBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        phoneNumTf = new javax.swing.JTextField();
        message = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("Delivery");

        jLabel2.setText("Full Name:");

        nameTf.setText("Shaowei Su");

        jLabel3.setText("Adress:");

        addressTf.setText("Piazza Leonardo da Vinci");

        jLabel4.setText("Zip Code:");

        zipTf.setText("20133");

        ConfirmBtn.setText("Confirm");
        ConfirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmBtnActionPerformed(evt);
            }
        });

        PreviousBtn.setText("Previous");
        PreviousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Phone Number:");

        phoneNumTf.setText("3290849941");
        phoneNumTf.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PreviousBtn)
                .addGap(25, 25, 25)
                .addComponent(ConfirmBtn)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(message))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameTf)
                            .addComponent(addressTf)
                            .addComponent(zipTf, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumTf, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(zipTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phoneNumTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfirmBtn)
                    .addComponent(PreviousBtn))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PreviousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousBtnActionPerformed
        CardLayout cl = (CardLayout)(allPanel.getLayout());
        cl.show(allPanel,"payment") ;
    }//GEN-LAST:event_PreviousBtnActionPerformed

    private void ConfirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmBtnActionPerformed
        String name = nameTf.getText();
        String address = addressTf.getText();
        String zipCode = zipTf.getText();
        String phone = phoneNumTf.getText();
        
        if(name.equals("") || address.equals("") ||
                zipCode.equals("") || phone.equals("")){
                message.setText("Please fill all the blanks!");
        }
        
        order.setDelivery_details(name+"\n"+address +
                                              "\n"+zipCode +"\n"+phone);
        
        try {
            
            boolean created = Order.createOrder(order);
            if(!created)
                setMessage("Failed to create the order!");
            else{
                orderDlg.dispose();
            }
                
            
        } catch (RemoteException ex) {
            Logger.getLogger(DeliveryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ConfirmBtnActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConfirmBtn;
    private javax.swing.JButton PreviousBtn;
    private javax.swing.JTextField addressTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel message;
    private javax.swing.JTextField nameTf;
    private javax.swing.JTextField phoneNumTf;
    private javax.swing.JTextField zipTf;
    // End of variables declaration//GEN-END:variables
}
