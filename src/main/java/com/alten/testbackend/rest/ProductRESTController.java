package com.alten.testbackend.rest;

import com.alten.testbackend.entities.Product;
import com.alten.testbackend.exception.ResourceNotFoundException;
import com.alten.testbackend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRESTController {

    private ProductService productService;

    public ProductRESTController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id){
        Product product = productService.findById(id);

        if (product==null){
            throw new ResourceNotFoundException("Product with the given id "+ id +" not found");
        }
        return product;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        /*
        *  In case an Id is set in the JSON, to prevent an update
        *  Force the Id to 0 (zero) to ensure a new item is created
        */
        product.setId(0);

        Product productToCreate = productService.save(product);

        return productToCreate;
    }

    @PatchMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id,
                                 @RequestBody Product productUpdate){

        Product productToUpdate = productService.findById(id);

        if (productToUpdate==null){
            throw new ResourceNotFoundException("Product with the given id "+ id +" not found");
        }

        Product productToSave = productService.save(productUpdate);

        return productToSave;
    }

    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable int id){

        Product productToDelete = productService.findById(id);

        if (productToDelete==null){
            throw new ResourceNotFoundException("Product with the given id "+ id +" not found");
        }

        productService.deleteById(id);

        return "Product successfully deleted";

    }
}
