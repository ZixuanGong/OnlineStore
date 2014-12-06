package gui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import utils.ServerInterface;


public class ManagerPanel extends javax.swing.JPanel {
    private final ServerInterface server;
    private ArrayList< Object[] > data;
    private ArrayList<Integer> prods2delete;
    private HashMap<Integer,Object[]> prods2update; //String[] (name,proc,mem,flash,price)
    
    public ManagerPanel(ServerInterface server) {
        this.server = server;
        data = new ArrayList<>();
        prods2delete = new ArrayList<>();
        prods2update = new HashMap<>();
                
        initComponents();
        refreshTable();
        setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        delPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        prodTable = new javax.swing.JTable();
        categoryCombo_del = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        submitBtn = new javax.swing.JButton();
        msg_del = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        categoryCombo_add = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        nameTf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        procTf = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        memoryTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        flashTf = new javax.swing.JTextField();
        priceLbl = new javax.swing.JLabel();
        priceTf = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        msg_add = new javax.swing.JLabel();
        isStdCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        prodTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(prodTable);

        categoryCombo_del.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laptops", "Desktops", "Servers" }));
        categoryCombo_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryCombo_delActionPerformed(evt);
            }
        });

        jLabel2.setText("Product Category:");

        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout delPanelLayout = new javax.swing.GroupLayout(delPanel);
        delPanel.setLayout(delPanelLayout);
        delPanelLayout.setHorizontalGroup(
            delPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(delPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addGroup(delPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(categoryCombo_del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, delPanelLayout.createSequentialGroup()
                        .addComponent(msg_del)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitBtn)))
                .addContainerGap())
        );
        delPanelLayout.setVerticalGroup(
            delPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(delPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(categoryCombo_del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(delPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBtn)
                    .addComponent(msg_del))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Update Products", delPanel);

        jLabel6.setText("Category:");

        categoryCombo_add.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laptops", "Desktops", "Servers" }));

        jLabel10.setText("Name:");

        nameTf.setColumns(20);

        jLabel11.setText("Processor:");

        procTf.setColumns(20);

        jLabel12.setText("Memory(GB):");

        memoryTf.setColumns(20);

        jLabel3.setText("Flash Storage(GB):");

        flashTf.setColumns(20);

        priceLbl.setText("Price($):");

        priceTf.setColumns(20);

        addBtn.setText("Submit");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        isStdCheckBox.setText("Is Standard");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(memoryTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(procTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(categoryCombo_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(priceLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(priceTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(flashTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(isStdCheckBox, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(254, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(msg_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn)
                        .addGap(102, 102, 102))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(categoryCombo_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(procTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(memoryTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(flashTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLbl)
                    .addComponent(priceTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(isStdCheckBox)
                .addGap(4, 4, 4)
                .addComponent(addBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg_add)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Add Product", jPanel2);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Manager");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        
        try {
            if(prods2delete.size() > 0){
                boolean del_result = server.deleteProducts(getSelectedCategory(categoryCombo_del), prods2delete);
                if(del_result){
                    refreshTable();
                    setMessage(msg_del,"Updated succussfully!");
                } else {
                    setMessage(msg_del,"Failed to update!");
                }
            }
            
            
            if(prods2update.size() > 0){
                boolean update_result = server.updateProducts(getSelectedCategory(categoryCombo_del),prods2update);
                if(update_result){
                    refreshTable();
                    setMessage(msg_del,"Updated succussfully!");
                } else {
                    setMessage(msg_del,"Failed to update!");
                }
            }
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(AccountantPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            
    }//GEN-LAST:event_submitBtnActionPerformed

    private void categoryCombo_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryCombo_delActionPerformed
        refreshTable();
    }//GEN-LAST:event_categoryCombo_delActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String name = nameTf.getText();
        String proc = procTf.getText();
        String memory = memoryTf.getText();
        String flash = flashTf.getText();
        String price = priceTf.getText();
        
        if(name.equals("") || proc.equals("")||
           memory.equals("") || flash.equals("") ||
           price.equals("")){
            
            setMessage(msg_add,"Please fill in all the blanks!");
        }
        
        else{
            int isStd=0;  
            if(isStdCheckBox.isSelected())
                isStd = 1;
            
            try {
                boolean added = server.insertProduct(getSelectedCategory(categoryCombo_add),
                        name,
                        proc,
                        Integer.parseInt(memory),
                        Integer.parseInt(flash),
                        Integer.parseInt(price),
                        isStd);
                
                if(added)
                    setMessage(msg_add,"Product added successfully!");
                else
                    setMessage(msg_add,"Failed to add the product!");
            } catch (RemoteException ex) {
                Logger.getLogger(ManagerPanel.class.getName()).log(Level.SEVERE, null, ex);
                setMessage(msg_add,"Failed to add the product!");
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox categoryCombo_add;
    private javax.swing.JComboBox categoryCombo_del;
    private javax.swing.JPanel delPanel;
    private javax.swing.JTextField flashTf;
    private javax.swing.JCheckBox isStdCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField memoryTf;
    private javax.swing.JLabel msg_add;
    private javax.swing.JLabel msg_del;
    private javax.swing.JTextField nameTf;
    private javax.swing.JLabel priceLbl;
    private javax.swing.JTextField priceTf;
    private javax.swing.JTextField procTf;
    private javax.swing.JTable prodTable;
    private javax.swing.JButton submitBtn;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

    private void refreshTable() {
                
        loadData4Tabel(getSelectedCategory(categoryCombo_del));
        prodTable.setModel(new ProdTabelModel());
    }
    
    private void loadData4Tabel(String cat) {
        try {
            data = server.getProds4Manager(cat);
            
        } catch (RemoteException ex) {
            Logger.getLogger(AccountantPanel.class.getName()).log(Level.SEVERE, null, ex);
            data = null;
        }
        
        if(data==null)
            setMessage(msg_del,"Failed to load data!");
        else
            setMessage(msg_del,"Data loaded!");
        
    }
    
    private void setMessage(final JLabel label,final String msg){
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run(){
                label.setText(msg) ;
            }
        }) ;
    }
    
    class ProdTabelModel extends AbstractTableModel{
        
        String columnNames[] = { "Prod_id", "Name","Processor","Memory","Flash","Price","Standard","Delete"};
        
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
        
        @Override
        public void setValueAt(Object value, int row, int column) {
            System.out.println("value="+value);
            Object prevValue = (data.get(row))[column];
            (data.get(row))[column] = value;
            int id = (Integer) (data.get(row))[0] ;
            
            //delete
            if(column == 7){
                
                if(value.equals(true))
                    prods2delete.add(id);
                else
                    prods2delete.remove((Integer)id); 
            } 
            //update
            else {
                if(column==1 || column==2){//check empty string
                     System.out.println("1 or 2");
                    if(value.toString().length()<1){
                        
                        JOptionPane.showMessageDialog(delPanel, "An empty field!", "Error", JOptionPane.ERROR_MESSAGE);
                        (data.get(row))[column] = prevValue;
                        return;
                    } 
                }        
                
                String name = (String) data.get(row)[1];
                String proc = (String) data.get(row)[2];
                boolean is_std = (Boolean) data.get(row)[6];
                
                try{                   
                    int mem = (Integer) data.get(row)[3];
                    int flash = (Integer) data.get(row)[4];
                    int price = (Integer) data.get(row)[5];
                    
                    prods2update.put(id, new Object[]{name,proc,mem,flash,price,is_std});
                    
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(delPanel, "An empty field!", "Error", JOptionPane.ERROR_MESSAGE);
                    (data.get(row))[column] = prevValue;
                }
            }
                    
        }
        @Override
        public boolean isCellEditable(int row, int column) {
            return (column >= 1);
          }
        
    }
    
    private String getSelectedCategory(JComboBox combo){
        String category;
        
        if(combo.getSelectedIndex() == 0)
            category = "laptops";
        else if(combo.getSelectedIndex() ==1)
            category = "desktops";
        else
            category = "servers";
        
        return category;
    }
}
