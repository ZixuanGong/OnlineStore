package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
**
*/
public class DatabaseHelper {
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/online_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    
    public static final int LOGIN_VALID = 1;
    public static final int WRONG_PW = 0;
    public static final int NO_SUCH_ROW = 2;
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    public DatabaseHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            
        } catch (Exception ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean loginSuperuser(String id, String pwInput){
        
        try {
            resultSet = statement.executeQuery("SELECT * FROM staff WHERE id='" + id +"'") ;
            if(resultSet.next()){
                
                String pw = (String)resultSet.getObject("password") ;
                
                if(pw.equals(pwInput))
                    return true ;
                else 
                    return false ;
            }
            else return false ;
        }
        catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false ;
        }
    }

    
    public int loginCustomer(String email, String pwInput){
         
        try {
            
            resultSet = statement.executeQuery("SELECT * FROM customers WHERE email='" + email +"'");
            
            if(resultSet.next()){
                //customer exists
                String pw = (String)resultSet.getObject("password");
                
                if(pw.equals(pwInput))
                    return 1;
                else
                    return 0;
                
            } else{
                //no such line
                return NO_SUCH_ROW;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } 
        
    }
    
    public ArrayList<Integer> getStdProdId(String category){
        //get computers of category(laptop/desktop/server) with standard config        
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + category+" WHERE is_standard=1 ");
            ArrayList<Integer> prodIdList = new ArrayList<>();
            
            while(resultSet.next()){
                prodIdList.add(resultSet.getInt(1));//the first col is id
            }
            
            return prodIdList;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
       public ArrayList<Integer> getOrderId(String email){
        //get email of the customers        
        try {
            resultSet = statement.executeQuery("SELECT * FROM order_list WHERE customer = '"+ email+"'");
            ArrayList<Integer> orderIdList = new ArrayList<>();
            
            while(resultSet.next()){
                orderIdList.add(resultSet.getInt(1));//the first col is id
            }
            
            return orderIdList;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
       
    public HashMap<String, String> getProdDetails(String category, int prodId){
        
        HashMap<String,String> prodDetails = new HashMap<>();
        
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + category + " WHERE id = " + prodId + " ");
            resultSet.next();//must be true
            
            prodDetails = getSSHashMapFromDb("name","processor","price");
            prodDetails.put("memory", appendUnit(resultSet.getInt("memory")));
            prodDetails.put("flash_storage",appendUnit(resultSet.getInt("flash_storage")));

            return prodDetails;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean updateProducts(String category, HashMap<Integer,Object[]> map){
        //String[] (name,proc,mem,flash,price)
        
        for(int id: map.keySet()){
            int result;
            
            String name = (String)map.get(id)[0];
            String proc = (String)map.get(id)[1];
            int mem = (Integer)map.get(id)[2];
            int flash = (Integer)map.get(id)[3];
            int price = (Integer)map.get(id)[4];
            boolean is_std = (Boolean)map.get(id)[5];
            
            try {
                result = statement.executeUpdate("UPDATE "+category+" SET name='"+name+
                        "',processor='"+proc+
                        "',memory="+mem+
                        ",flash_storage="+flash+
                        ",price="+price +
                        ",is_standard="+(is_std?1:0)+" WHERE id = " + id + " ");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
            if(result==0)
                return false;
        }
        return true;
    }
    
    public HashMap<String, String> getOrderDetails(int orderId){
        
        HashMap<String,String> orderDetails = new HashMap<>();
        
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + "order_list" + " WHERE id = " + orderId + " ");
            resultSet.next();
            
            orderDetails = getSSHashMapFromDb("product","payment","delivery","status");
            orderDetails.put("price", "$"+resultSet.getInt("price"));

            return orderDetails;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
            
    
    public HashMap<String,Object> getComponentList(String category, String prodName){
        try {
            String sql = "SELECT * FROM "+category+" WHERE name='"+prodName+"'";
            resultSet = statement.executeQuery(sql);
            
            HashMap<String,Object> list = new HashMap<>();
            
            TreeSet<String> processorList = new TreeSet();//use treeset to avoid duplicates & sort the list
            TreeSet<Integer> memoryList = new TreeSet();
            TreeSet<Integer> flashStorageList = new TreeSet();
                        
            while(resultSet.next()){
                processorList.add(resultSet.getString("processor"));
                memoryList.add(resultSet.getInt("memory"));
                flashStorageList.add(resultSet.getInt("flash_storage"));
            }
            
             
            list.put("processor", processorList);
            list.put("memory",appendUnit(memoryList));
            list.put("flash_storage", appendUnit(flashStorageList));
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int getIdByConfig(String category, String prodName, String proc, int memory, int flashStorage) {
        try {
            String sql = "SELECT id FROM "+category+" WHERE name='"+prodName+"' and processor='"+proc+
                    "' and memory="+memory+" and flash_storage="+flashStorage+" ";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int getPriceById(String cat,int prodId) {
        try {
            resultSet = statement.executeQuery("SELECT price FROM "+cat+" WHERE id="+prodId+" ");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public boolean createOrder(HashMap<String, String> order){
        try {
            String customer= order.get("customer");
            String product = order.get("product");
            String payment=order.get("payment") ;
            String delivery = order.get("delivery");
            String status = order.get("status");
            String price = order.get("price");
            
            if(customer==null||product==null|
                    payment==null|delivery==null|
                    status==null|price==null){
                return false;
            } else {
            
                int create = statement.executeUpdate("INSERT INTO order_list (customer,product,payment,delivery,status,price)"
                        + " VALUES ('"+customer+"','"+product
                        + "','" +payment+"','" + delivery
                        + "','" + status+"',"+ Integer.parseInt(price) +")");

                if(create>0)
                    return true;
                else 
                    return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     

    private ArrayList<String> appendUnit(TreeSet<Integer> treeSet) {
        //e.g. flash storage: 128->128GB 1024->1T
        ArrayList<String> listWithUnits = new ArrayList(); 
        
        for(int i:treeSet){
                if(i<1024)
                    listWithUnits.add(i+"GB");
                else
                    listWithUnits.add(i/1024 + "TB");
            }  
        return listWithUnits;
    }
    
    private String appendUnit(int i){
        if(i<1024)
            return i+"GB";
        else
            return i/1024 + "TB";
    }

    private HashMap<String,String> getSSHashMapFromDb(String... keys) throws SQLException {
        HashMap<String,String> map = new HashMap<>();
        
        for(String key: keys)
            map.put(key, resultSet.getString(key));
        
        return map;
    }

    public boolean registerCustomer(String email, String password) {

        try {
            int create = statement.executeUpdate("INSERT INTO customers (email,password)"
                    + " VALUES ('"+email+"','"+password+"')");
            
            if(create>0)
                return true;
            else 
                return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public boolean cancelOrder(int orderId) {
        
        try {
            int cancel = statement.executeUpdate("UPDATE order_list SET status='canceled' WHERE id = " + orderId + " ") ;
            if(cancel == 1)
                return true ;
            else
                return false;


        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public ArrayList<Object[]> getOrders4Accountant(String paymentMethod){
        try {
            //get all the UNPAID orders
            //"Order id", "Customer","Payment","Price"
            resultSet = statement.executeQuery("SELECT id,customer,payment,price FROM order_list WHERE status='unpaid' and payment='"+paymentMethod+"'");
            ArrayList<Object[]> list = new ArrayList<>();
            
            if(resultSet.next()){
                do{
                    Object[] row = {resultSet.getInt("id"),
                                    resultSet.getString("customer"),
                                    resultSet.getString("payment"),
                                    "$"+resultSet.getString("price"),
                                    false}; //last item corresponds to the checkbox
                    list.add(row);
                    
                }while(resultSet.next());
                
            } 
            //no unpaid orders
//            else
//                list.add(new Object[]{"*","*","*","*",false});
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Object[]> getOrders4WHKeeper(){
        try {
            //get all the PAID orders
            //"Order id", "Customer","Payment","Price"
            resultSet = statement.executeQuery("SELECT id,customer,payment,price FROM order_list WHERE status='paid'");
            ArrayList<Object[]> list = new ArrayList<>();
            
            if(resultSet.next()){
                do{
                    Object[] row = {resultSet.getInt("id"),
                                    resultSet.getString("customer"),
                                    resultSet.getString("payment"),
                                    "$"+resultSet.getString("price"),
                                    false}; //last item corresponds to the checkbox
                    list.add(row);
                    
                }while(resultSet.next());
                
            } 
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Object[]> getProds4Manager(String category){
        try {
            //"Prod_id", "Name","Processor","Memory(GB)","Flash(GB)","Price($)","Standard","Delete"
            resultSet = statement.executeQuery("SELECT * FROM " + category +" ");
            ArrayList<Object[]> list = new ArrayList<>();
            
            while(resultSet.next()){
                //get is_standard
                boolean is_std = false;
                if(resultSet.getInt("is_standard") == 1){
                    is_std = true;
                }
                
                Object[] row = {resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("processor"),
                                resultSet.getInt("memory"),
                                resultSet.getInt("flash_storage"),
                                resultSet.getInt("price"),
                                is_std,
                                false}; //last item corresponds to the checkbox
                list.add(row);
                    
            } 
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean updateStatus(ArrayList<Integer> orders, String status){
        
        int result;
        
        for(int id : orders){
            try {
                result = statement.executeUpdate("UPDATE order_list SET status='"+status+"' WHERE id="+id+" ");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
                result = 0;
            }
            
            //result can only be 1 or 0, and should be 1
            //once an error(0) occurred, return false
            if(result==0)
               return false;
        }
        return true;
    }
    
    public boolean deleteProducts(String category, ArrayList<Integer> prods2delete){
        
        for(int id : prods2delete){
            try {
                statement.executeUpdate("DELETE FROM "+category +" WHERE id="+id+" ");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }
    
    
    
    public boolean insertProduct(String category, String name, String proc, int mem, int flash, int price, int isStd){
        try {
            int create = statement.executeUpdate("INSERT INTO "+category+ " (name,processor,memory,flash_storage,price,is_standard)"
                    + " VALUES ('"+name+"','"+proc+ "',"+mem+"," + flash+","
                    + price +","+isStd+")");
            
            if(create>0)
                return true;
            else 
                return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}
