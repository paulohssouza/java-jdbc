package br.com.aluraoracle.t5one;

import br.com.aluraoracle.t5one.dao.CategoryDAO;
import br.com.aluraoracle.t5one.dao.ProductDAO;
import br.com.aluraoracle.t5one.model.Category;
import br.com.aluraoracle.t5one.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListingCategoryTest {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = new ConnectionFactory().triggersConnection()) {
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            List<Category> categoryList = categoryDAO.listing();
            categoryList.forEach(
                    category -> {
                        System.out.println(category);
                        try {
                            for (Product product : new ProductDAO(connection).search(category)
                                 ) {
                                System.out.println(category.getName() +
                                        " - " + product.getName());
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

        }
    }
}
