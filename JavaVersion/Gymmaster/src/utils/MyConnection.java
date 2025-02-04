package utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class MyConnection {
    public String url ="jdbc:mysql://localhost:3306/pishop";
    public String login ="root";
    public String pwd ="";
    Connection cnx;
    public static MyConnection instance;
    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
}
