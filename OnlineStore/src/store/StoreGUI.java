package store;

import utils.Computer;
import gui.CustomerDetails;
import gui.LoginDialog;
import gui.OrderDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import utils.ServerInterface;


public class StoreGUI extends javax.swing.JFrame {
    
    public static ServerInterface server;//is it appropriate to claim server as protected static?
    private String customerEmail;
    private static final String TOMCAT_URL = "http://localhost:8080/online_store/";
    String path ;
    
    
    public StoreGUI(ServerInterface server) throws IOException {
        this.server = server;
        init();
       
        setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        laptopTab = new javax.swing.JPanel();
        laptopPanel = new javax.swing.JScrollPane();
        desktopTab = new javax.swing.JPanel();
        desktopPanel = new javax.swing.JScrollPane();
        serverTab = new javax.swing.JPanel();
        serverPanel = new javax.swing.JScrollPane();
        ordersBtn = new javax.swing.JButton();
        message = new javax.swing.JLabel();

        setTitle("Computer Store");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        javax.swing.GroupLayout laptopTabLayout = new javax.swing.GroupLayout(laptopTab);
        laptopTab.setLayout(laptopTabLayout);
        laptopTabLayout.setHorizontalGroup(
            laptopTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(laptopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        laptopTabLayout.setVerticalGroup(
            laptopTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(laptopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Laptop", laptopTab);

        javax.swing.GroupLayout desktopTabLayout = new javax.swing.GroupLayout(desktopTab);
        desktopTab.setLayout(desktopTabLayout);
        desktopTabLayout.setHorizontalGroup(
            desktopTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        desktopTabLayout.setVerticalGroup(
            desktopTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Desktop", desktopTab);

        javax.swing.GroupLayout serverTabLayout = new javax.swing.GroupLayout(serverTab);
        serverTab.setLayout(serverTabLayout);
        serverTabLayout.setHorizontalGroup(
            serverTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        serverTabLayout.setVerticalGroup(
            serverTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverPanel)
        );

        jTabbedPane1.addTab("Server", serverTab);

        ordersBtn.setText("My Orders");
        ordersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersBtnActionPerformed(evt);
            }
        });

        message.setText("Welcome!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ordersBtn)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(message)
                    .addComponent(ordersBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ordersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersBtnActionPerformed
        new CustomerDetails(StoreGUI.this,customerEmail);
    }//GEN-LAST:event_ordersBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane desktopPanel;
    private javax.swing.JPanel desktopTab;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane laptopPanel;
    private javax.swing.JPanel laptopTab;
    private javax.swing.JLabel message;
    private javax.swing.JButton ordersBtn;
    private javax.swing.JScrollPane serverPanel;
    private javax.swing.JPanel serverTab;
    // End of variables declaration//GEN-END:variables

    private void init() throws MalformedURLException, IOException {
        initComponents();
//        path = "http://officeimg.vo.msecnd.net/en-us/images/MH900441471.jpg" ;
//        URL url = new URL(path);
//        BufferedImage image = ImageIO.read(url);
//        
//        logo.setIcon(new ImageIcon(image));
//        logo.setSize(5, 5);
//        logo.setOpaque(false);
        //load data from server, while let user login
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        threadExecutor.execute(new LoadDataTask());
        threadExecutor.execute(new Runnable() {

            @Override
            public void run() {
                new LoginDialog(StoreGUI.this,StoreGUI.this);
            }
        });
    }
    
    public void setCustomer(String email){
        customerEmail = email;
        message.setText("Welcome! "+email);
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
        
    
    class LoadDataTask implements Runnable{

        @Override
        public void run() {
            try {
                
                loadDataForTab(laptopPanel,"laptops");
                loadDataForTab(desktopPanel,"desktops");
                loadDataForTab(serverPanel,"servers");                
                
            } catch (Exception ex) {
                Logger.getLogger(StoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                laptopPanel.add(new JLabel("Error"));
            }
            
            
        }

        private void loadDataForTab(final JScrollPane tabPanel, String category) throws RemoteException, IOException {
            
            ArrayList<Integer> products = Computer.getStdProdId(category);//get prod id
                
//            Box prodList = Box.createVerticalBox();
            JPanel prodList = new JPanel();
            prodList.setLayout(new BoxLayout(prodList, BoxLayout.Y_AXIS));
            
            //create a panel for each prod
            for(int prodId : products){
                HashMap<String,String> details = Computer.getProdDetails(category,prodId);   
                BufferedImage image = getImageByName(details.get("name"));

                Box prodPanel = Box.createHorizontalBox();
                prodPanel.setMaximumSize(new Dimension(600, 120));
                prodPanel.setOpaque(true);
                prodPanel.setBackground(Color.white);
                prodPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                //-------------------image
                JLabel picLabel = new JLabel(new ImageIcon(image));
                picLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                prodPanel.add(picLabel);

                //-------------------description
                JTextArea descr = new JTextArea();
                descr.setAlignmentY(Component.CENTER_ALIGNMENT);
                descr.setEditable(false);
                descr.setText("Name: " + details.get("name") +
                        "\nProcessor: "+details.get("processor") +
                        "\nMemory: "+details.get("memory") + 
                        "\nFlash Storage: "+details.get("flash_storage"));
                prodPanel.add(descr);                           

                //-------------------price
                JLabel priceLabel = new JLabel("$"+ details.get("price"));
                priceLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                prodPanel.add(priceLabel);
                
                //-------------------button
                JButton selectBtn = new JButton("Select");
                selectBtn.setActionCommand(category + "/" + details.get("name")); //using space as splitter
                selectBtn.addActionListener(new SelectBtnListener());
                selectBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
                prodPanel.add(selectBtn);

                
                prodList.add(Box.createRigidArea(new Dimension(0,10)));
                prodList.add(prodPanel);

            }//end for loop

            tabPanel.setViewportView(prodList);

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    tabPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    tabPanel.revalidate();
                    tabPanel.repaint();
                }
            });
        }
        
    } 
    
    public BufferedImage getImageByName(String name) throws UnsupportedEncodingException, IOException{
        String picName = name.replaceAll("\\s+","");//tomcat has prob with url that has spaces in it!
        String url = TOMCAT_URL + picName + ".png";
        return ImageIO.read(new URL(url));
    }
    
    private class SelectBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();//category+" "+prodName
            String[] temp = command.split("/");
            
            String category = temp[0];
            String name = temp[1];
            
            new OrderDialog(StoreGUI.this,customerEmail,category, name);
        }
        
    }
    
    
}
