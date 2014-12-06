package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import utils.Bank;
import utils.Order;

/**this panel sets the order's paymentMethod, changes status
 * 
 * 
 */

public class PaymentPanel extends javax.swing.JPanel {
    private OrderDialog orderDlg;
    private JPanel allPanel;
    private Order order;
    
    private JPanel cards ;

    public PaymentPanel(OrderDialog dialog) {
        orderDlg = dialog;
        this.order = orderDlg.getOrder();
        this.allPanel = orderDlg.getAllPanel();
        
        
        init() ;
        
    }
    
    public void init(){
        initComponents();
        
        ItemHandler buttonHandler = new ItemHandler() ;

        credCardBtn.addItemListener(buttonHandler);
        cashBtn.addItemListener(buttonHandler) ;
        bankTransBtn.addItemListener(buttonHandler) ;  
        
        cards = new JPanel(new CardLayout());
        cards.add(creditCardPanel, "Credit Card");
        cards.add(bankTransPanel, "Bank Transfer");
        cards.add(cashPanel, "Cash") ; 
        
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.add(cards,BorderLayout.CENTER);
        detailsPanel.validate();
        detailsPanel.repaint();
        
    }
    
    private class ItemHandler implements ItemListener
    {
        
        @Override
        public void itemStateChanged(ItemEvent evt) {
            if(credCardBtn.isSelected()){
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, credCardBtn.getText());
            } else if(cashBtn.isSelected()){
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, cashBtn.getText());
            } else if(bankTransBtn.isSelected()){
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, bankTransBtn.getText());
            }
            
        }
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

        suibian = new javax.swing.ButtonGroup();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        credCardBtn = new javax.swing.JRadioButton();
        credCardBtn.setSelected(true);
        cashBtn = new javax.swing.JRadioButton();
        bankTransBtn = new javax.swing.JRadioButton();
        PreviousBtn = new javax.swing.JButton();
        NextBtn = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        creditCardPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        credit_cardNumTb = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        credit_cardHolderTb = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        credit_expDateTb = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        credit_cvvTb = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        bankTransPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cashPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        jLabel12.setText("jLabel12");

        setPreferredSize(new java.awt.Dimension(624, 430));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("Payment");

        suibian.add(credCardBtn);
        credCardBtn.setText("Credit Card");

        suibian.add(cashBtn);
        cashBtn.setText("Cash");

        suibian.add(bankTransBtn);
        bankTransBtn.setText("Bank Transfer");

        PreviousBtn.setText("Previous");
        PreviousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousBtnActionPerformed(evt);
            }
        });

        NextBtn.setText("Next");
        NextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Card Number:");

        credit_cardNumTb.setText("123");

        jLabel3.setText("Card Holder:");

        credit_cardHolderTb.setText("test");

        jLabel4.setText("Expire Date:");

        credit_expDateTb.setText("test");

        jLabel5.setText("CVV");

        credit_cvvTb.setText("123");

        jLabel11.setText("[mm/yyyy]");

        javax.swing.GroupLayout creditCardPanelLayout = new javax.swing.GroupLayout(creditCardPanel);
        creditCardPanel.setLayout(creditCardPanelLayout);
        creditCardPanelLayout.setHorizontalGroup(
            creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creditCardPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(message)
                    .addGroup(creditCardPanelLayout.createSequentialGroup()
                        .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(creditCardPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel11))
                            .addGroup(creditCardPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(credit_cardNumTb, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(credit_expDateTb, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(credit_cardHolderTb, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(credit_cvvTb, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        creditCardPanelLayout.setVerticalGroup(
            creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creditCardPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(credit_cardNumTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(credit_cardHolderTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(creditCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(credit_expDateTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(credit_cvvTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(message)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel13.setText("Bank: Bank of China");

        jLabel6.setText("IBAN:JSDFHO82346HHJJ");

        jLabel7.setText("BIC-code:171238");

        jLabel8.setText("Beneficiary:Mario Rosso");

        javax.swing.GroupLayout bankTransPanelLayout = new javax.swing.GroupLayout(bankTransPanel);
        bankTransPanel.setLayout(bankTransPanelLayout);
        bankTransPanelLayout.setHorizontalGroup(
            bankTransPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bankTransPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(bankTransPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(bankTransPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(112, 112, 112))
        );
        bankTransPanelLayout.setVerticalGroup(
            bankTransPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bankTransPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(bankTransPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7))
                .addGap(26, 26, 26)
                .addGroup(bankTransPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel10.setText("Please finish the payment at our official agents.");

        javax.swing.GroupLayout cashPanelLayout = new javax.swing.GroupLayout(cashPanel);
        cashPanel.setLayout(cashPanelLayout);
        cashPanelLayout.setHorizontalGroup(
            cashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel10)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        cashPanelLayout.setVerticalGroup(
            cashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel10)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(creditCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(detailsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cashPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
            .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(detailsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(bankTransPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(creditCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(detailsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cashPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(70, Short.MAX_VALUE)))
            .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(detailsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(bankTransPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bankTransBtn)
                            .addComponent(cashBtn)
                            .addComponent(credCardBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(458, Short.MAX_VALUE)
                .addComponent(PreviousBtn)
                .addGap(25, 25, 25)
                .addComponent(NextBtn)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(detailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(credCardBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cashBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bankTransBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(detailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreviousBtn)
                    .addComponent(NextBtn))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PreviousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousBtnActionPerformed
        CardLayout cl = (CardLayout)(allPanel.getLayout());
        cl.show(allPanel,"configuration") ;
    }//GEN-LAST:event_PreviousBtnActionPerformed

    private void NextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextBtnActionPerformed
        
        //-------------credit card
        if(credCardBtn.isSelected()){
            // store the input values of credit cards
            String cardNum = credit_cardNumTb.getText();
            String holder = credit_cardHolderTb.getText();
            String cvv = credit_cvvTb.getText();
            String exp = credit_expDateTb.getText();
            
            //validate if user has filled all the blanks
            if(holder.equals("") || cardNum.equals("") ||
                cvv.equals("") || exp.equals("")){
                setMessage("Please fill all the blanks!");
            }
            
            else try {
                //verify payment info
                boolean cardVerified = Bank.verifyCreditCardInfo(Integer.valueOf(cardNum), holder, Integer.valueOf(cvv), exp);
                
                //correct card info
                if(cardVerified){
                    //process payment, change status if succeed
                    boolean paymentProcessed = Bank.processPayment(Integer.valueOf(cardNum), order.getPrice() );
                    
                    if(paymentProcessed){
                        order.setPaymentMethod("Credit Card");
                        order.setStatus(Order.PAID);
                        goToDelivery();
                    } else 
                        setMessage("Payment failed!");
                } 
                //wrong card info
                else {
                    setMessage("Wrong credit card information");
                }
                
                
                                        
            } catch (RemoteException ex) {
                Logger.getLogger(PaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        //--------------Bank transfer
        else if(bankTransBtn.isSelected()){
            order.setPaymentMethod("Bank Transfer") ;
            order.setStatus(Order.UNPAID) ;
            goToDelivery();
        } 
        //--------------cash...how to realize?
        else if(cashBtn.isSelected()){
            order.setPaymentMethod("Cash") ;
            order.setStatus(Order.UNPAID) ;
            goToDelivery();
        }
        
    }//GEN-LAST:event_NextBtnActionPerformed

    private void goToDelivery(){
        CardLayout cl = (CardLayout)(allPanel.getLayout());
        cl.show(allPanel,"delivery") ; 
    }
        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NextBtn;
    private javax.swing.JButton PreviousBtn;
    private javax.swing.JRadioButton bankTransBtn;
    private javax.swing.JPanel bankTransPanel;
    private javax.swing.JRadioButton cashBtn;
    private javax.swing.JPanel cashPanel;
    private javax.swing.JRadioButton credCardBtn;
    private javax.swing.JPanel creditCardPanel;
    private javax.swing.JTextField credit_cardHolderTb;
    private javax.swing.JTextField credit_cardNumTb;
    private javax.swing.JTextField credit_cvvTb;
    private javax.swing.JTextField credit_expDateTb;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel message;
    private javax.swing.ButtonGroup suibian;
    // End of variables declaration//GEN-END:variables
}
