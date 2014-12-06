package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
//import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import store.StoreGUI;
import utils.Customer;
import utils.Order;

public class CustomerDetails extends javax.swing.JDialog {

    ExecutorService threadExecutor;
    private String email;

    public CustomerDetails(Frame frame, String email) {
        super(frame, true);
        initComponents();

        this.email = email;

        threadExecutor = Executors.newCachedThreadPool();
        threadExecutor.execute(new LoadDataTask());
        setVisible(true);
    }



    class LoadDataTask implements Runnable {

        @Override
        public void run() {
            try {
                ArrayList<Integer> orders = Order.getOrderId(email);

                final JPanel allOrders = new JPanel();
                allOrders.setBackground(Color.gray);
                allOrders.setLayout(new BoxLayout(allOrders, BoxLayout.Y_AXIS));
                
                for (final int orderId : orders) {

                    HashMap<String, String> orderDetail = Order.getOrderDetails(orderId);
                    
                    final JPanel orderPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
                    orderPanel.setBackground(Color.white);
                    orderPanel.setOpaque(true);

                    JTextArea prodDetails = new JTextArea();
                    prodDetails.setText("Computer details:\n" + orderDetail.get("product"));
                    
                    JTextArea deliveryDetails = new JTextArea();
                    deliveryDetails.setText("Delivery details:\n" + orderDetail.get("delivery"));
                    
                    JTextArea paymentDetails = new JTextArea();
                    paymentDetails.setText(orderDetail.get("payment")+"\n"+orderDetail.get("price"));
                    
                    JLabel statusLbl = new JLabel();
                    statusLbl.setText(orderDetail.get("status"));
                    
                    orderPanel.add(prodDetails);
                    orderPanel.add(deliveryDetails);
                    orderPanel.add(paymentDetails);
                    orderPanel.add(statusLbl);
                    
                        
                    JButton cancelButton ;
                    cancelButton = new JButton("Cancel") ;
                    orderPanel.add(cancelButton,BorderLayout.EAST) ;
                    
                    if (orderDetail.get("status").equals("canceled") )
                        cancelButton.setVisible(false);

                    cancelButton.addActionListener(new ActionListener () {
                        
                        @Override
                        public void actionPerformed(ActionEvent event)
                        {
                            try {
                                Order.cancelOrder(orderId) ;

                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {

                                        threadExecutor = Executors.newCachedThreadPool();
                                        threadExecutor.execute(new LoadDataTask());
                                    }
                                });                                  
                            } 

                            catch (RemoteException ex) {
                                Logger.getLogger(CustomerDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    
                    if(orderDetail.get("status").equals("shipped"))
                        cancelButton.setEnabled(false);
                    
                    allOrders.add(orderPanel,BorderLayout.WEST);

                }//end for loop
                orderPanel.setViewportView(allOrders);

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        orderPanel.revalidate();
                        orderPanel.repaint();
                    }
                });

            } catch (Exception ex) {
                Logger.getLogger(StoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                orderPanel.add(new JLabel("Error"));
            }

        }

    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orderPanel = new javax.swing.JScrollPane();

        setTitle("My Orders");
        setMinimumSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane orderPanel;
    // End of variables declaration//GEN-END:variables
}
