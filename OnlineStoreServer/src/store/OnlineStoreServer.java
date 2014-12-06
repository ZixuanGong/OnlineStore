package store;


import utils.ServerImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ServerInterface;


public class OnlineStoreServer {
	
	
    public static void main(String[] args){
        try {
            
            ServerInterface server = new ServerImpl(); // server object
            Registry registry = LocateRegistry.createRegistry(12345);
            registry.rebind("OnlineStoreServer", server); //registry server object
            
        } catch (RemoteException ex) {
            Logger.getLogger(OnlineStoreServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
