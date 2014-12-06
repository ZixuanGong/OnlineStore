
package utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import store.StoreGUI;


public class Computer {

    String category;
    int prodId;
    int price;
    HashMap<String, String> config = new HashMap<String,String>();
    
    
    public static ArrayList<Integer> getStdProdId(String category) throws RemoteException{
        return StoreGUI.server.getStdProdId(category);
        
    }
    
    public static HashMap<String, String> getProdDetails(String category, int prodId) throws RemoteException{
        return StoreGUI.server.getProdDetails(category,prodId);
    }
    
    public static HashMap<String,Object> getComponentList(String category, String prodName) throws RemoteException{
        return StoreGUI.server.getComponentList(category, prodName);
    }
    
    public static int getIdByConfig(String category, String prodName, String proc, String mem, String flash) throws RemoteException {
        //memory and flashStorage are strings with units, so need to be processed to get the int value
        int memory = Integer.valueOf(mem.replace("GB", ""));
        int flashStorage;
        
        if(flash.contains("TB"))
            flashStorage = 1024 * Integer.valueOf(flash.replace("TB", ""));
        else
            flashStorage = Integer.valueOf(flash.replace("GB",""));
        
        return StoreGUI.server.getIdByConfig(category,prodName,proc,memory,flashStorage);
    }
    
    public static int getPriceById(String cat, int prodId) throws RemoteException {
        return StoreGUI.server.getPriceById(cat, prodId);
    }

    
}
