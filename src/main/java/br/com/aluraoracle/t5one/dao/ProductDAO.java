package br.com.aluraoracle.t5one.dao;

import br.com.aluraoracle.t5one.model.Product;

import java.sql.*;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Product product) throws SQLException {
        String mySql = "insert into tbproduct(name, description) values(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(mySql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    product.setId(resultSet.getInt(1));
                }
            }
        }
    }
}
