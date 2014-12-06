package store;

import gui.LoginDialog;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ServerInterface;

public class OnlineStore {
    
    
    public static void main(String args[]){
        
       try {
           //connect to server
            //System.setSecurityManager(new RMISecurityManager());
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",12345); //localhost
            ServerInterface server = (ServerInterface) registry.lookup("OnlineStoreServer"); //look for server object, return server object
            
            new StoreGUI(server);
            
        } catch (Exception ex) {
            Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
       
       
       
    }
    
   
}
