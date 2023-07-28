package br.com.aluraoracle.t5one;

import java.sql.Connection;
import java.sql.SQLException;

public class TestsConnection {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.triggersConnection();
        System.out.println("Fechando conexão.");
        connection.close();
    }
}
