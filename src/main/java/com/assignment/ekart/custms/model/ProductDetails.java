package com.assignment.ekart.custms.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDetails {
    @NotNull(message = "{cartProduct.productId.absent}")
    private Integer productId;
    private String name;
    private String description;
    private String category;
    private String brand;
    private Double price;
    private Integer availableQuantity;
}
