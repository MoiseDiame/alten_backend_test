package com.alten.testbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;
    @NotNull
    private String code;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private float price;
    @NotNull
    private int quantity;

    private String inventoryStatus;
    @NotNull
    private String category;
    private String image;
    private float rating;
}
