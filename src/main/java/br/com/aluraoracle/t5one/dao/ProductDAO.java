package br.com.aluraoracle.t5one.dao;

import br.com.aluraoracle.t5one.model.Category;
import br.com.aluraoracle.t5one.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Product> productList() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String mySql = "select id, name, description from tbproduct";
        try(PreparedStatement preparedStatement = connection.prepareStatement(mySql)) {
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while(resultSet.next()) {
                    Product product = new Product(resultSet.getInt(1),
                            resultSet.getNString(2), resultSet.getString(3) );
                    productList.add(product);
                }
                return productList;
            }
        }
    }

    public List<Product> search(Category category) throws SQLException {
        List<Product> productList = new ArrayList<>();
        String mySql = "select id, name, description from tbproduct where category_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(mySql)) {
            preparedStatement.setInt(1, category.getId());
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while(resultSet.next()) {
                    Product product = new Product(resultSet.getInt(1),
                            resultSet.getNString(2), resultSet.getString(3) );
                    productList.add(product);
                }
                return productList;
            }
        }
    }
}
