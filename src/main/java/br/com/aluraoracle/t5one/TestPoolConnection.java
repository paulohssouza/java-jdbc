package br.com.aluraoracle.t5one;

import java.sql.Connection;
import java.sql.SQLException;

public class TestPoolConnection {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        for(int i = 0; i < 20; i ++) {
            connectionFactory.triggersConnection();
            System.out.println("Conexão de número: " + i);
        }
    }
}
