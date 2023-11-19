package com.alten.testbackend.service;

import com.alten.testbackend.dto.ProductDTO;
import com.alten.testbackend.entities.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();
    ProductDTO findById(int id);

    ProductDTO save (ProductDTO product);


    void deleteById(int id);
}
