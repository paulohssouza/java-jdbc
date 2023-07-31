package br.com.aluraoracle.t5one.controller;

import br.com.aluraoracle.t5one.dao.CategoryDAO;
import br.com.aluraoracle.t5one.factory.ConnectionFactory;
import br.com.aluraoracle.t5one.model.Category;

import java.sql.Connection;
import java.util.List;

public class CategoryController {
    private final CategoryDAO categoryDAO;

    public CategoryController() {
        Connection connection = new ConnectionFactory().triggersConnection();
        this.categoryDAO = new CategoryDAO(connection);
    }

    public List<Category> list() {
        return this.categoryDAO.list();
    }
}
