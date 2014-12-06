package utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import store.StoreGUI;

public class Order {
    public static final String UNPAID="unpaid" ;
    public static final String PAID="paid";
    public static final String PROCESSING="processing";
    public static final String SHIPPED="shipped";
    public static final String DELIVERED="delivered";
    public static final String CANCELED="canceled";
    
    private String customer;//email
    private String category;
    private int prodId;
    private String paymentMethod;
    private int price;
    private String delivery_details;
    private String status;

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setDelivery_details(String delivery_details) {
        this.delivery_details = delivery_details;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setProdId(int prodId){
        this.prodId = prodId;
    }
    
    public void setPrice(int price){
        this.price = price; 
    }
    
    public int getPrice(){
        return this.price;
    }
    
    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
    
   
    public static ArrayList<Integer> getOrderId(String email) throws RemoteException{
        return StoreGUI.server.getOrderId(email);
        
    }
    
    public static HashMap<String, String> getOrderDetails(int orderId) throws RemoteException{
        return StoreGUI.server.getOrderDetails(orderId);
 
    }
    
    public static boolean createOrder(Order order) throws RemoteException{
        HashMap<String,String> config = Computer.getProdDetails(order.category, order.prodId);
        String product = "Product ID: "+order.prodId +"\n"+config.get("name")+
                            "\n"+config.get("processor") +"\nMemory: "+config.get("memory")+
                            "\nFlash Storage: "+config.get("flash_storage");
        String payment = order.paymentMethod;
        
        
        HashMap<String,String> newOrder = new HashMap<>();
        newOrder.put("customer", order.customer);
        newOrder.put("product",product);
        newOrder.put("payment",payment);
        newOrder.put("delivery", order.delivery_details);
        newOrder.put("status", order.status);
        newOrder.put("price", order.price+"");
        
        return StoreGUI.server.createOrder(newOrder);
    }
    
    public static boolean cancelOrder(int orderId) throws RemoteException{
        return StoreGUI.server.cancelOrder(orderId) ;
    }
}
