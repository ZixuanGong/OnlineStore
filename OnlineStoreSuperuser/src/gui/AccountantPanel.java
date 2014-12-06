
package gui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import utils.ServerInterface;


public class AccountantPanel extends javax.swing.JPanel {

    private ServerInterface server;
    private ArrayList< Object[] > data;
    private ArrayList<Integer> ordersPaid;
    
    public AccountantPanel(ServerInterface server) {
        this.server = server;
        data = new ArrayList<>();
        ordersPaid = new ArrayList<>();
        
        initComponents();
        refreshTable(); 
        
        setVisible(true);
    }
    
    private void setMessage(final String msg){
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run(){
                message.setText(msg) ;
            }
        }) ;
    }

    private void loadData4Tabel(String paymentMethod) {
        try {
            data = server.getOrders4Accountant(paymentMethod);
            
        } catch (RemoteException ex) {
            Logger.getLogger(AccountantPanel.class.getName()).log(Level.SEVERE, null, ex);
            data = null;
        }
        
        if(data==null)
                setMessage("Failed to load data!");
            else if(data.isEmpty())
                setMessage("No unpaid orders!");
            else
                setMessage("Data loaded!");
        
        
    }
    
    class OrderTabelModel extends AbstractTableModel{
        
        String columnNames[] = { "Order_id", "Customer","Payment","Price","Selected"};//last one is "selected"
        
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
          }
        
        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
        
        @Override
        public Class getColumnClass(int column) {
        return (getValueAt(0, column).getClass());
        }

        @Override
        public Object getValueAt(int row, int column) {
            return (data.get(row))[column];
        }
        
        //setValueAt() won't be called when initializing the table
        @Override
        public void setValueAt(Object value, int row, int column) {
            (data.get(row))[column] = value;
            int id = (Integer) (data.get(row))[0] ;//get id of the selected order
            
            //checkbox is selected, which means before this operation, the value is FALSE
            if(value.equals(true))
                ordersPaid.add(id);
            
            //checkbox is unchecked, this order_id needs to be removed from the arraylist
            else
                ordersPaid.remove((Integer)id);            
            
        }
        
        public boolean isCellEditable(int row, int column) {
            return (column == 4);
          }
        
    }
    
    private void refreshTable(){
        String paymentMethod;
        
        if(cashBtn.isSelected())
            paymentMethod = "Cash";
        else
            paymentMethod = "Bank Transfer";
        
        loadData4Tabel(paymentMethod);
        orderTabel.setModel(new OrderTabelModel());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTabel = new javax.swing.JTable();
        confirmBtn = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cashBtn = new javax.swing.JRadioButton();
        bankTransBtn = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(568, 365));

        orderTabel.setModel(new OrderTabelModel());
        jScrollPane1.setViewportView(orderTabel);

        confirmBtn.setText("Confirm Payment");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Payment Method:");

        btnGroup.add(cashBtn);
        cashBtn.setText("Cash");
        cashBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashBtnActionPerformed(evt);
            }
        });

        btnGroup.add(bankTransBtn);
        bankTransBtn.setText("Bank Transfer");
        bankTransBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankTransBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("宋体", 1, 12)); // NOI18N
        jLabel2.setText("Listed are all the UNPAID orders:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Accountant");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmBtn)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(cashBtn)
                        .addGap(18, 18, 18)
                        .addComponent(bankTransBtn))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cashBtn)
                    .addComponent(bankTransBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmBtn)
                    .addComponent(message))
                .addGap(22, 22, 22))
        );

        cashBtn.setSelected(true);
    }// </editor-fold>//GEN-END:initComponents

    private void cashBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashBtnActionPerformed
        refreshTable();
    }//GEN-LAST:event_cashBtnActionPerformed

    private void bankTransBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankTransBtnActionPerformed
        refreshTable();
    }//GEN-LAST:event_bankTransBtnActionPerformed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
          
        boolean result=false;
        
        try {
            result = server.updateStatus(ordersPaid,"paid");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountantPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(result){
            refreshTable();
            setMessage("Confirmed succussfully!");
        } else 
            setMessage("Error occurred in payment confirmation!");
        
    }//GEN-LAST:event_confirmBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bankTransBtn;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JRadioButton cashBtn;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel message;
    private javax.swing.JTable orderTabel;
    // End of variables declaration//GEN-END:variables
}
