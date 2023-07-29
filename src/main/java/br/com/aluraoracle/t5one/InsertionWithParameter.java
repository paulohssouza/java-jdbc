package br.com.aluraoracle.t5one;

import java.sql.*;

public class InsertionWithParameter {
    public static void main(String[] args) throws SQLException {
        String name = "Mouse";
        String description = "Mouse sem fio.";
        Connection connection = ConnectionFactory.triggersConnection();
        PreparedStatement statement = connection.prepareStatement("insert into tbproduct" +
                "(name, description) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.setString(2, description);
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            System.out.println("ID criado: " + id);
        }
    }
}
