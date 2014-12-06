package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bank {
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/bank";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    public Bank(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            
        } catch (Exception ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean verifyCreditCardInfo(int cardNum, String holder, int cvv, String expDate){
        String sql = "SELECT * FROM credit_card WHERE number="+cardNum+" and holder='" + holder +
                      "' and cvv=" +cvv +" and exp_date='"+expDate+"'";
        
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet.next())
                return true;
            else
                return false;
                        
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean processPayment(int cardNum, int price) {
        try {
            resultSet = statement.executeQuery("SELECT credit_limit FROM credit_card WHERE number=" + cardNum +" ");
            resultSet.next();
            
            int limit = resultSet.getInt("credit_limit");
            if(limit >= price){
                int moneyLeft = limit-price;
                statement.executeUpdate("UPDATE credit_card SET credit_limit="+moneyLeft+" WHERE number="+cardNum+" ");
                return true;
            } else
                return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
