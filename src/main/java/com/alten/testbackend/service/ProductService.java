package com.alten.testbackend.service;

import com.alten.testbackend.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(int id);

    Product save (Product product);

    void deleteById(int id);
}
