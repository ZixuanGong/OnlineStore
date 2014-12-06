
package superuser;

import gui.LoginDialog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ServerInterface;


public class OnlineStoreSuperuser {

    
    public static void main(String[] args) {
        try {
            
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345) ; 
            ServerInterface server = (ServerInterface) registry.lookup("OnlineStoreServer") ;
            
            new LoginDialog(server) ;
        }
        catch (Exception ex) {
            Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex) ;
        }
    }
    
}
