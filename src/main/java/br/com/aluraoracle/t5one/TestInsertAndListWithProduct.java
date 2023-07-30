package br.com.aluraoracle.t5one;

import br.com.aluraoracle.t5one.dao.ProductDAO;
import br.com.aluraoracle.t5one.model.Product;

import java.sql.*;
import java.util.List;

public class TestInsertAndListWithProduct {
    public static void main(String[] args) throws SQLException {
        Product smartTv = new Product("Samsumg SmartTv 360",
                "A mais moderna smarttv que você vai ver!");
        try(Connection connection = new ConnectionFactory().triggersConnection()) {
            ProductDAO productDAO = new ProductDAO(connection);
            productDAO.save(smartTv);
            List<Product> productList = productDAO.productList();
            productList.forEach(System.out::println);
        }
    }
}
