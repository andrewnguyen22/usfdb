package App;

import Controller.Global;

import java.sql.*;
import java.util.Properties;

public class DB_Tools {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        System.out.println("Connection attempt started.");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Class Registered");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/usfdb", "root", "root");
        System.out.println("Connected to database");
        return conn;
    }
}
