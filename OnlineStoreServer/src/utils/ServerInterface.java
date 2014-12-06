package utils;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;


public interface ServerInterface extends Remote{
    public boolean cancelOrder( int orderId ) throws RemoteException ;
    public boolean registerUser(String email, String password) throws RemoteException;
    public boolean authenticateSuperuser(String email, String password) throws RemoteException;
    public int authenticateUser(String email, String password) throws RemoteException;
    public ArrayList<Integer> getStdProdId(String category) throws RemoteException;
    public HashMap<String, String> getProdDetails(String category, int prodId) throws RemoteException;
    public ArrayList<Integer> getOrderId(String email) throws RemoteException ;
    public HashMap<String, String> getOrderDetails(int orderId) throws RemoteException ;
    public HashMap<String,Object> getComponentList(String category, String prodName) throws RemoteException;
    public int getIdByConfig(String category, String prodName, String proc, int memory, int flashStorage) throws RemoteException;
    public int getPriceById(String cat,int prodId) throws RemoteException;
    public boolean verifyCreditCardInfo(int cardNum, String holder, int cvv, String expDate) throws RemoteException;
    public boolean processPayment(int cardNum, int price) throws RemoteException;
    public boolean createOrder(HashMap<String, String> newOrder) throws RemoteException;
    public ArrayList< Object[] > getOrders4Accountant(String paymentMethod) throws RemoteException;
    public ArrayList< Object[] > getOrders4WHKeeper() throws RemoteException;
    public ArrayList< Object[] > getProds4Manager(String category) throws RemoteException;
    public boolean updateStatus(ArrayList<Integer> orders, String status) throws RemoteException;
    public boolean updateProducts(String category, HashMap<Integer,Object[]> prods) throws RemoteException;
    public boolean deleteProducts(String category,ArrayList<Integer> prods2delete) throws RemoteException;
    public boolean insertProduct(String category,String name,String proc, int mem, int flash, int price,int isStd) throws RemoteException;
}