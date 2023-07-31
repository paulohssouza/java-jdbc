package br.com.aluraoracle.t5one.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Integer id;
    private String name;
    private List<Product> productList;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.productList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", produtos= " + productList +
                '}';
    }
}
