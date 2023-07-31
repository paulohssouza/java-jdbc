package br.com.aluraoracle.t5one.controller;

import br.com.aluraoracle.t5one.dao.ProductDAO;
import br.com.aluraoracle.t5one.factory.ConnectionFactory;
import br.com.aluraoracle.t5one.model.Product;

import java.sql.Connection;
import java.util.List;

public class ProductController {
    private final ProductDAO productDAO;

    public ProductController() {
        Connection connection = new ConnectionFactory().triggersConnection();
        this.productDAO = new ProductDAO(connection);
    }

    public List<Product> list() {
        return this.productDAO.list();
    }

    public void save(Product product) {
        this.productDAO.save(product);
    }

    public void delete(Integer id) {
        this.productDAO.delete(id);
    }

    public void update(String name, String description, Integer id) {
        this.productDAO.update(name, description, id);
    }
}
