package com.alten.testbackend.service;

import com.alten.testbackend.dao.ProductDAO;
import com.alten.testbackend.dto.ProductDTO;
import com.alten.testbackend.dto.ProductMapper;
import com.alten.testbackend.entities.Product;
import com.alten.testbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{


    private ProductDAO productDAO;
    private ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO ,
                              ProductMapper productMapper){
        this.productDAO = productDAO;
        this.mapper = productMapper;
    }
    @Override
    public List<ProductDTO> findAll() {

        return productDAO.findAll()
                .stream().map(mapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(int id) {

        Product product = productDAO.findById(id);

        if (product == null) {
            throw new ResourceNotFoundException ("Product with the given id "+ id +" not found");
        } else{
            return mapper.productToProductDTO(productDAO.findById(id));
        }

    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO product) {
        return mapper.productToProductDTO(productDAO.save(mapper.productDTOToProduct(product)));
    }


    @Override
    @Transactional
    public void deleteById(int id) {

        Product productToDelete = productDAO.findById(id);

        if (productToDelete == null) {
            throw new ResourceNotFoundException ("Product with the given id "+ id +" not found");
        } else {
            productDAO.deleteById(id);
        }
    }
}
