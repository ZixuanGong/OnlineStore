package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


public class BankTest {
    static Bank bank;
    
    public BankTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         bank= new Bank();
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
     * Test of verifyCreditCardInfo method, of class Bank.
     */
//    @Ignore
    @Test
    public void testVerifyCreditCardInfo() {
        System.out.println("verifyCreditCardInfo");
        int cardNum = 123;
        String holder = "test";
        int cvv = 123;
        String expDate = "test";
        
        boolean expResult = true;
        boolean result = bank.verifyCreditCardInfo(cardNum, holder, cvv, expDate);
        assertEquals(expResult, result);
        
        cardNum = 111;
        result = bank.verifyCreditCardInfo(cardNum, holder, cvv, expDate);
        assertFalse(result);
    }

    /**
     * Test of processPayment method, of class Bank.
     */
    @Test
    public void testProcessPayment() {
        System.out.println("processPayment");
        int cardNum = 123;
        int price = 1;
        boolean expResult = true;
        boolean result = bank.processPayment(cardNum, price);
        assertEquals(expResult, result);
    }
    
}
