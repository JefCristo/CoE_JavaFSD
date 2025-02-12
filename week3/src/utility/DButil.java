package utility;

import java.sql.*;


public class DButil {
    
    private static final String URL = "jdbc:mysql://localhost:3306/fee_report";
    private static final String USERNAME = "root";  
    private static final String PASSWORD = "jeffy";  

    public static Connection getConnection() throws SQLException {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Add the connector JAR to your classpath.");
            e.printStackTrace();
        }
       
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    
}
