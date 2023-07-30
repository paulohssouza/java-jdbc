package br.com.aluraoracle.t5one;

import java.sql.*;

public class InsertionWithParameter {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(Connection connection = connectionFactory.triggersConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into tbproduct" +
                    "(name, description) values(?, ?)", Statement.RETURN_GENERATED_KEYS)){

                insertItem(statement, "SmartTV", "45 polegadas");
                insertItem(statement, "Radio", "Radio a pilha");
                insertItem(statement, "Cafeteira", "Cafeteira com cápsulas");
                insertItem(statement, "liquidificador", "liquidificador multiprocessador");

                connection.commit();

            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("Rollback executado");
                connection.rollback();
            }
        }
    }

    private static void insertItem(PreparedStatement statement, String name, String description) throws SQLException {
        statement.setString(1, name);
        statement.setString(2, description);
        statement.execute();

        try(ResultSet resultSet = statement.getGeneratedKeys()) {
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println("ID criado: " + id);
            }
        }
    }
}
