package gui ;

import java.awt.CardLayout;
import java.awt.Frame;
import javax.swing.JPanel;
import store.StoreGUI;
import utils.Computer;
import utils.Order;


public class OrderDialog extends javax.swing.JDialog {
    private JPanel allPanel;
    private Order order;
    
    
    public OrderDialog(Frame frame,String customer,String category, String prodName) {
        super(frame,true);
        initComponents();
        
        order = new Order();
        order.setCustomer(customer);
       
        //----prepare cardslayout
        allPanel = new JPanel(new CardLayout());
        
        allPanel.add(new ConfigurationPanel(OrderDialog.this,category,prodName),"configuration") ;
        allPanel.add(new PaymentPanel(OrderDialog.this),"payment") ;
        allPanel.add(new DeliveryPanel(OrderDialog.this),"delivery") ;
        
        setContentPane(allPanel);
        setVisible(true);
    }

    public Order getOrder() {
        return order;
    }

    public JPanel getAllPanel() {
        return allPanel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
