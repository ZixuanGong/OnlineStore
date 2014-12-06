package utils;


import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import store.StoreGUI;


public class Customer {
    public static final int LOGIN_VALID = 1;
    public static final int WRONG_PW = 0;
    public static final int NO_SUCH_ROW = 2;
                
    private String email;
    private int phoneNumber;
    

    
    public static int login(String email, String password) throws RemoteException{
        
        return StoreGUI.server.authenticateUser(email, password);
       
    }
    
    public static HashMap<String,String> loadCustomerData(String email){
        try {
            return StoreGUI.server.loadCustomerData(email);
        } catch (RemoteException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean register(String email, String password) throws RemoteException{
        
        return StoreGUI.server.registerUser(email, password) ;
    }
    
    
}
