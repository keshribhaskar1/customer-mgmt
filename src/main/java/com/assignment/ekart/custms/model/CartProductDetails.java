package com.assignment.ekart.custms.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CartProductDetails {
    private Integer cartProductId;
    @Valid
    private ProductDetails product;
    @PositiveOrZero(message = "{cartProduct.invalid.quantity}")
    private Integer quantity;
}
