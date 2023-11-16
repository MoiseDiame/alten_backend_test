package com.alten.testbackend.dao;

import com.alten.testbackend.entities.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(int id);

    Product save (Product product);

    void deleteById(int id);
}
