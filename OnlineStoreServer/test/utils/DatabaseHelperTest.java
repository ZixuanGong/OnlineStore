package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class DatabaseHelperTest {
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/online_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    
    static DatabaseHelper db;
    static Statement statement;
    static ResultSet resultSet;
    static Connection connection;
    
    public DatabaseHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        db = new DatabaseHelper();
        
        try {           
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loginCustomer method, of class DatabaseHelper.
     */
    @Test
    public void testLoginCustomer() {
        System.out.println("loginCustomer");
        // valid case
        
        String email = "test";
        String pwInput = "test";
        int expResult = 1;
        int result = db.loginCustomer(email, pwInput);
        assertEquals(expResult, result);
        
        // wrong password
        
        email = "test" ;
        pwInput = "balabala" ;
        expResult = 0 ;
        result = db.loginCustomer(email, pwInput);
        
        // not exists customer
        
        email = "balabala" ;
        pwInput = "test" ;
        expResult = 2 ;
        result = db.loginCustomer(email, pwInput);

        
    }


    /**
     * Test of getPriceById method, of class DatabaseHelper.
     */
    
    @Test
    public void testGetPriceById() {
        System.out.println("getPriceById");
        String cat = "servers";
        int prodId = 4;
        int expResult = 1299;
        int result = db.getPriceById(cat, prodId);
        assertEquals(expResult, result);
    }

    /**
     * Test of createOrder method, of class DatabaseHelper.
     */
//    @Ignore
    @Test
    public void testCreateOrder() {
        System.out.println("createOrder");
        
        //------------valid input
        HashMap<String,String> newOrder = new HashMap<>();
        newOrder.put("customer", "test");
        newOrder.put("product","test");
        newOrder.put("payment","Cash");
        newOrder.put("delivery", "test");
        newOrder.put("status", "unpaid");
        newOrder.put("price","1234");
        
        boolean result = db.createOrder(newOrder);
        assertTrue(result);
        
        //------------with empty fields
        newOrder.clear();
        newOrder.put("customer", "test");
        newOrder.put("payment","Cash");
        newOrder.put("status", "unpaid");
        newOrder.put("price","1234");
        result = db.createOrder(newOrder);
        assertFalse(result);
    }

    /**
     * Test of loginSuperuser method, of class DatabaseHelper.
     */
    @Test
    public void testLoginSuperuser() {
        System.out.println("loginSuperuser");
        
        //valid case
        String id = "1";
        String pwInput = "test";
        boolean expResult = true;
        boolean result = db.loginSuperuser(id, pwInput);
        assertEquals(expResult, result);
        
        //wrong password
        id = "1" ;
        pwInput = "bala";
        expResult = false ;
        result = db.loginSuperuser(id, pwInput);
        assertEquals(expResult, result);
        
        //id that not exist
        id = "42323" ;
        pwInput = "bala" ;
        expResult = false ;
        result = db.loginSuperuser(id, pwInput);
        assertEquals(expResult, result);        
        
    }


    /**
     * Test of registerCustomer method, of class DatabaseHelper.
     */
    @Test
    public void testRegisterCustomer() {
        System.out.println("registerCustomer");
        String email = "123@123.com";
        String password = "balabala";
        boolean result = db.registerCustomer(email, password);
        assertTrue(result);
        
        try {
            statement.executeUpdate("DELETE FROM customers WHERE email='" + email + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of cancelOrder method, of class DatabaseHelper.
     */
//    @Ignore
    @Test
    public void testCancelOrder() {
        System.out.println("cancelOrder");
        int orderId = 1;
 
        //-----------change status correctly
        boolean result = db.cancelOrder(orderId);
        assertTrue(result);
        
        try {
            resultSet = statement.executeQuery("SELECT status FROM order_list WHERE id=" + orderId + " ");
            resultSet.next();
            assertEquals(resultSet.getString(1), "canceled");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //-----------order doesn't exist
        orderId = -1;
        result = db.cancelOrder(orderId);
        assertFalse(result);
    }

    /**
     * Test of updateStatus method, of class DatabaseHelper.
     */
    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        
        //-------------valid situation
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        
        boolean expResult = true;
        boolean result = db.updateStatus(idList, "shipped");
        assertEquals(expResult, result);
        
        for(int id : idList){
            try {
                resultSet = statement.executeQuery("SELECT status FROM order_list WHERE id=" + id + " ");
                resultSet.next();
                assertEquals(resultSet.getString(1), "shipped");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelperTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //invalid id input
        idList.clear();
        idList.add(100);
        for(int id : idList){
            try {
                resultSet = statement.executeQuery("SELECT status FROM order_list WHERE id=" + id + " ");
                assertFalse(resultSet.next());
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelperTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test of updateProducts method, of class DatabaseHelper.
     */
    @Test
    public void testUpdateProducts() {
        System.out.println("updateProducts");
        String category = "desktops";
        HashMap<Integer,Object[]> map = new HashMap();
        Object[] array = {"test","test",8,2048,1234,false};
        map.put(5,array);
        
        boolean result = db.updateProducts(category, map);
        assertTrue(result);
    }
    
}
