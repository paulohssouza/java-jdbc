package br.com.aluraoracle.t5one.dao;

import br.com.aluraoracle.t5one.model.Category;

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
}
