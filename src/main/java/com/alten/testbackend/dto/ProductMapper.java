package com.alten.testbackend.dto;

import com.alten.testbackend.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct (ProductDTO productDTO);
}
