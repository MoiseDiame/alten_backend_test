package com.alten.testbackend.rest;

import com.alten.testbackend.dto.ProductDTO;
import com.alten.testbackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductRESTController {

    private ProductService productService;

    public ProductRESTController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<ProductDTO> getAllProducts(){
        return productService.findAll();
    }


    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable int id){

        return productService.findById(id);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO product){
        /*
        *  In case an Id is set in the JSON, to prevent an update
        *  Force the Id to 0 (zero) to ensure a new item is created
        */
        product.setId(0);

        ProductDTO productToCreate = productService.save(product);

        return ResponseEntity.ok(productToCreate);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id,
                                 @Valid @RequestBody ProductDTO productUpdate){

        /*
         * Checking first if the id provided is valid if not Exception Thrown
         * */
        ProductDTO productToUpdate = productService.findById(id);


        ProductDTO UpdatedProduct = productUpdate;

        /*
         * Making sure to update the product with the given id and not creating a new one
         */
        UpdatedProduct.setId(id);



        return ResponseEntity.ok(productService.save(UpdatedProduct));
    }

    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable int id){

        productService.deleteById(id);

        return "Product successfully deleted";

    }
}
