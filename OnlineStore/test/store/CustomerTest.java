//
//package store;
//
//import gui.LoginDialog;
//import utils.ServerInterface;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//
//public class CustomerTest {
//    Customer customer;
//    ServerInterface server;
//    
//    public CustomerTest() {
//    }
//    
//    
//    @Before
//    public void setUp() {
//        try {
//            Registry registry = LocateRegistry.getRegistry("127.0.0.1",12345); //localhost
//            server = (ServerInterface) registry.lookup("OnlineStoreServer");
//        } catch (Exception ex) {
//            Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        
//        customer = new Customer();
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    
//    /**
//     * Test of login method, of class Customer.
//     */
//    @Test
//    public void testLogin() throws Exception {
//        System.out.println("login");
//        String email = "";
//        String password = "";
//        Customer instance = customer;
//        boolean expResult = false;
//        boolean result = instance.login(email, password);
//        assertEquals(expResult, result);
//        
//        email = "test";
//        password = "test";
//        expResult = true;
//        result = instance.login(email, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }
//    
//}
