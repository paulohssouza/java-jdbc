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

    public void save(Product product) {
        String mySql = "insert into tbproduct(name, description, category_id) values(?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(mySql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCategoryId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    product.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<Product> list() {
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
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<Product> search(Category category) throws SQLException {
        List<Product> productList = new ArrayList<>();
        String mySql = "select id, name, description from tbproduct where category_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(mySql)) {
            preparedStatement.setInt(1, category.getId());
            preparedStatement.execute();
            transformResultsetIntoProduct(preparedStatement, productList);
            return productList;
        }
    }

    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from tbproduct where id = ?"
        )) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update(String name, String description, Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "update tbproduct p set p.name = ?, p.description = ? where id = ?"
        )) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static void transformResultsetIntoProduct(PreparedStatement preparedStatement, List<Product> productList) throws SQLException {
        try(ResultSet resultSet = preparedStatement.getResultSet()) {
            while(resultSet.next()) {
                Product product = new Product(resultSet.getInt(1),
                        resultSet.getNString(2), resultSet.getString(3) );
                productList.add(product);
            }
        }
    }
}
