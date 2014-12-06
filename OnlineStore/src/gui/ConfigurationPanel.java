package gui;

import java.awt.CardLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import utils.Computer;
import utils.Order;

/**
 * this panel sets the order's category,prodId and price
 * 
 */
public class ConfigurationPanel extends javax.swing.JPanel {
    private OrderDialog orderDlg;
    private JPanel allPanel;
    private Order order;
    
    private final String category;
    private int prodId;
    private final String prodName;
    private int price;
    
    public ConfigurationPanel(OrderDialog dialog, String category, String prodName) {
        orderDlg = dialog;
        this.order = orderDlg.getOrder();
        this.allPanel = orderDlg.getAllPanel();
        this.category = category;
        this.prodName = prodName;
        
        initComponents();
        
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new LoadConfigTask());
    }

    class LoadConfigTask implements Runnable{

        @Override
        public void run() {
            
            try {
                final HashMap<String,Object> componentLists =  Computer.getComponentList(category,prodName);
                
                
                SwingUtilities.invokeLater(new Runnable(){

                    @Override
                    public void run() {
                        nameLabel.setText(prodName);

                        //load comboBox
                        TreeSet<String> list1 = (TreeSet<String>)componentLists.get("processor");                
                        processorCombo.setModel(new DefaultComboBoxModel(list1.toArray()));
                        ArrayList<String> list2 = (ArrayList<String>)componentLists.get("memory");
                        memoryCombo.setModel(new DefaultComboBoxModel(list2.toArray()));
                        list2 = (ArrayList<String>)componentLists.get("flash_storage");  
                        flashCombo.setModel(new DefaultComboBoxModel(list2.toArray()));
                        
                        updatePriceAndId();
                        
                    }
                    
                });
                
            } catch (RemoteException ex) {
                Logger.getLogger(ConfigurationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    private void updatePriceAndId() {
        try {
            //update id
            prodId = Computer.getIdByConfig(category,
                                        prodName,
                                        processorCombo.getSelectedItem().toString(),
                                        memoryCombo.getSelectedItem().toString(),
                                        flashCombo.getSelectedItem().toString());
            
            //update price
            price = Computer.getPriceById(category,prodId);
            if (price != -1) {
               
  
            SwingUtilities.invokeLater(new Runnable(){
                
                @Override
                public void run() {
                    priceLabel.setText("$"+price);
                }
            });
            nextBtn.setVisible(true);
            }
            else {
            SwingUtilities.invokeLater(new Runnable(){
                
                @Override
                public void run() {
                    priceLabel.setText("There is no such configuration! Please try agian");
                }
            }); 
            
            nextBtn.setVisible(false);
            }
            
        } catch (RemoteException ex) {
            Logger.getLogger(ConfigurationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        memoryCombo = new javax.swing.JComboBox();
        flashCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        processorCombo = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(624, 430));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("Configuration");

        memoryCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        memoryCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memoryComboActionPerformed(evt);
            }
        });

        flashCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        flashCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flashComboActionPerformed(evt);
            }
        });

        jLabel2.setText("Processor:");

        jLabel3.setText("Memory:");

        jLabel4.setText("Flash Storage:");

        nextBtn.setText("Proceed to Check Out");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Computer:");

        processorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        processorCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processorComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(flashCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(memoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel)
                            .addComponent(processorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(206, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLabel)
                    .addComponent(nextBtn))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nameLabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(processorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(flashCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(priceLabel)
                .addGap(18, 18, 18)
                .addComponent(nextBtn)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        
        order.setCategory(category);
        order.setProdId(prodId);
        order.setPrice(price);
        
        CardLayout cl = (CardLayout)(allPanel.getLayout());
        cl.show(allPanel,"payment") ;
    }//GEN-LAST:event_nextBtnActionPerformed

    private void processorComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processorComboActionPerformed
        updatePriceAndId();
    }//GEN-LAST:event_processorComboActionPerformed

    private void memoryComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memoryComboActionPerformed
        updatePriceAndId();
    }//GEN-LAST:event_memoryComboActionPerformed

    private void flashComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flashComboActionPerformed
        updatePriceAndId();
    }//GEN-LAST:event_flashComboActionPerformed
    
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox flashCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox memoryCombo;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton nextBtn;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JComboBox processorCombo;
    // End of variables declaration//GEN-END:variables
}
