package utils;

import java.rmi.RemoteException;
import store.StoreGUI;


public class Bank {
    public static boolean verifyCreditCardInfo(int cardNum, String holder, int cvv, String expDate) throws RemoteException{
        return StoreGUI.server.verifyCreditCardInfo(cardNum,holder,cvv,expDate);
    }

    public static boolean processPayment(int num, int price) throws RemoteException{
        return StoreGUI.server.processPayment(num,price);
    }
    
    
}
