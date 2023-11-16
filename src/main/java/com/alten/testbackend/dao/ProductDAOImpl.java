package com.alten.testbackend.dao;

import com.alten.testbackend.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{

    private EntityManager entityManager;

    @Autowired
    public ProductDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Product> findAll() {

        TypedQuery<Product> query = entityManager.createQuery("from Product",Product.class);
        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public Product findById(int id) {

        return entityManager.find(Product.class,id);
    }

    @Override
    public Product save(Product product) {

        Product productToSave = entityManager.merge(product);

        return productToSave;
    }

    @Override
    public void deleteById(int id) {

        Product productToDelete = entityManager.find(Product.class,id);

        entityManager.remove(productToDelete);

    }
}
