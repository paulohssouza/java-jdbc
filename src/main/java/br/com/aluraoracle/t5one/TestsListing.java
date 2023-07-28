package br.com.aluraoracle.t5one;

import java.sql.*;

public class TestsListing {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.triggersConnection();
        Statement statement = connection.createStatement();
        boolean result = statement.execute("select id, name, description from tbproduct");
        System.out.println(result);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("ID = " + id);
            String name = resultSet.getString("name");
            System.out.println("Nome = " + name);
            String description = resultSet.getString("description");
            System.out.println("Descrição = " + description);
        }
        connection.close();
    }
}
