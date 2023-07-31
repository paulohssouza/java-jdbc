package br.com.aluraoracle.t5one.dao;

import br.com.aluraoracle.t5one.model.Category;
import br.com.aluraoracle.t5one.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;
    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Category> listing() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        String mySql = "select id, name from tbcategory";
        try(PreparedStatement preparedStatement = connection.prepareStatement(mySql)) {
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while(resultSet.next()) {
                    Category category = new Category(
                            resultSet.getInt(1), resultSet.getString(2)
                    );
                    categoryList.add(category);
                }
            }
        }
        return categoryList;
    }

    public List<Category> listCategoryWithProducts() throws SQLException{
        Category categoryAux = null;
        List<Category> categoryList = new ArrayList<>();
        String mySql = "select c.id, c.name, p.id, p.name, p.description" +
                " from tbcategory c inner join " +
                "tbproduct p on c.id = p.category_id";
        try(PreparedStatement preparedStatement = connection.prepareStatement(mySql)) {
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while(resultSet.next()) {
                    if(categoryAux == null || !categoryAux.getName().equals(resultSet.getString(2))) {
                        Category category = new Category(
                                resultSet.getInt(1), resultSet.getString(2)
                        );
                        categoryAux = category;
                        categoryList.add(category);
                    }
                    Product product = new Product(resultSet.getInt(3),
                            resultSet.getString(4), resultSet.getString(5) );
                    categoryAux.getProductList().add(product);
                }
            }
        }
        return categoryList;
    }
}
