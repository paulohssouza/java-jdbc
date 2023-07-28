package br.com.aluraoracle.t5one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection triggersConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping?user=root", "root", "B@rbudo8594");
    }
}
