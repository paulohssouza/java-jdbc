package br.com.aluraoracle.t5one;

import br.com.aluraoracle.t5one.model.Product;

import java.sql.*;

public class TestInsertWithProduct {
    public static void main(String[] args) throws SQLException {
        Product smartTv = new Product("Samsumg SmartTv 360",
                "A mais moderna smarttv que você vai ver!");
        try(Connection connection = new ConnectionFactory().triggersConnection()) {
            String mySql = "insert into tbproduct(name, description) values(?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(mySql,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, smartTv.getName());
                preparedStatement.setString(2, smartTv.getDescription());
                preparedStatement.execute();
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    while(resultSet.next()) {
                        smartTv.setId(resultSet.getInt(1));
                    }
                }
            }
        }
        System.out.println(smartTv);
    }
}
