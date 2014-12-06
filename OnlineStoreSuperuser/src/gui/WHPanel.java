
package gui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import utils.ServerInterface;


public class WHPanel extends javax.swing.JPanel {

    private final ServerInterface server;
    private ArrayList< Object[] > data;
    private ArrayList<Integer> ordersDelivered;
    
    public WHPanel(ServerInterface server) {
        this.server = server;
        data = new ArrayList<>();
        ordersDelivered = new ArrayList<>();
        
        initComponents();
        loadData4Tabel();
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

    private void loadData4Tabel() {
        
        try {
            data = server.getOrders4WHKeeper();
            
        } catch (RemoteException ex) {
            Logger.getLogger(WHPanel.class.getName()).log(Level.SEVERE, null, ex);
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
                ordersDelivered.add(id);
            
            //checkbox is unchecked, this order_id needs to be removed from the arraylist
            else
                ordersDelivered.remove((Integer)id);            
            
        }
        
        public boolean isCellEditable(int row, int column) {
            return (column == 4);
          }
        
    }
    
    private void refreshTabel(){
        
        loadData4Tabel();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(568, 365));

        orderTabel.setModel(new OrderTabelModel());
        jScrollPane1.setViewportView(orderTabel);

        confirmBtn.setText("Confirm ");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("宋体", 1, 12)); // NOI18N
        jLabel2.setText("Listed are all the PAID orders:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Warehouse Keeper");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(377, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmBtn)
                    .addComponent(message))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
          
        boolean result=false;
        
        try {
            result = server.updateStatus(ordersDelivered,"shipped");
        } catch (RemoteException ex) {
            Logger.getLogger(WHPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(result){
            refreshTabel();
            setMessage("Confirmed succussfully!");
        } else 
            setMessage("Error occurred in payment confirmation!");
        
    }//GEN-LAST:event_confirmBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel message;
    private javax.swing.JTable orderTabel;
    // End of variables declaration//GEN-END:variables
}
