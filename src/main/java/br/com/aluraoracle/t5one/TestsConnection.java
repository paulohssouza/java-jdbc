package br.com.aluraoracle.t5one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestsConnection {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping?user=root", "root", "B@rbudo8594");
        System.out.println("Fechando conexão.");
        connection.close();
    }
}
