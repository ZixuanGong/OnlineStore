package utils;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
   
    private String email;
    private String password;
    private DatabaseHelper dbHelper;
    private Bank bank;

    public ServerImpl() throws RemoteException {
        
        dbHelper = new DatabaseHelper();
        bank = new Bank();
    
    }

    @Override
    public int authenticateUser(String email, String password) throws RemoteException{
        
        return dbHelper.loginCustomer(email, password);
    }


    @Override
    public ArrayList<Integer> getStdProdId(String category) throws RemoteException {
        
        return dbHelper.getStdProdId(category);
    }

    @Override
    public HashMap<String, String> getProdDetails(String category, int prodId) throws RemoteException {
        return dbHelper.getProdDetails(category,prodId);
    }

    @Override
    public HashMap<String,Object> getComponentList(String category, String prodName) throws RemoteException {
        return dbHelper.getComponentList(category, prodName);
    }

    @Override
    public ArrayList<Integer> getOrderId(String email) throws RemoteException {
        return dbHelper.getOrderId(email); 
    }

    @Override
    public HashMap<String, String> getOrderDetails(int orderId) throws RemoteException {
         return dbHelper.getOrderDetails(orderId) ;   
    }

    @Override
    public int getIdByConfig(String category, String prodName, String proc, int memory, int flashStorage) throws RemoteException {
        return dbHelper.getIdByConfig(category,prodName,proc,memory,flashStorage);
    }

    @Override
    public int getPriceById(String cat,int prodId) throws RemoteException {
        return dbHelper.getPriceById(cat,prodId);
    }

    @Override
    public boolean verifyCreditCardInfo(int cardNum, String holder, int cvv, String expDate) throws RemoteException {
        return bank.verifyCreditCardInfo(cardNum, holder, cvv, expDate);
    }

    @Override
    public boolean processPayment(int cardNum, int price) throws RemoteException {
        return bank.processPayment(cardNum,price);
    }

    @Override
    public boolean createOrder(HashMap<String, String> order) throws RemoteException {
        
        return dbHelper.createOrder(order);
    }


    @Override
    public boolean registerUser(String email, String password) throws RemoteException {
    
        return dbHelper.registerCustomer(email,password) ;
    }

    @Override
    public boolean cancelOrder(int orderId) throws RemoteException {
        
        return dbHelper.cancelOrder(orderId );
    }

    @Override
    public boolean authenticateSuperuser(String email, String password) throws RemoteException {

        return dbHelper.loginSuperuser(email, password) ;
    
    }

    @Override
    public ArrayList<Object[]> getOrders4Accountant(String paymentMethod) throws RemoteException {
        return dbHelper.getOrders4Accountant(paymentMethod);
    }

    @Override
    public boolean updateStatus(ArrayList<Integer> orders, String status) throws RemoteException {
        return dbHelper.updateStatus(orders,status);
    }

    @Override
    public ArrayList<Object[]> getOrders4WHKeeper() throws RemoteException {

        return dbHelper.getOrders4WHKeeper() ;
    }

    @Override
    public ArrayList<Object[]> getProds4Manager(String category) throws RemoteException {
        return dbHelper.getProds4Manager(category);
    }

    @Override
    public boolean deleteProducts(String category, ArrayList<Integer> prods2delete) throws RemoteException {
        return dbHelper.deleteProducts(category,prods2delete);
    }

    @Override
    public boolean insertProduct(String category, String name, String proc, int mem, int flash, int price, int isStd) throws RemoteException {
        return dbHelper.insertProduct(category,name,proc,mem,flash,price,isStd);
    }

    @Override
    public boolean updateProducts(String category, HashMap<Integer, Object[]> prods) throws RemoteException {
        return dbHelper.updateProducts(category, prods);
    }
    
}
