package com.assignment.ekart.custms.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Set;

@Data
public class CustomerCartDetails {
    private Integer cartId;
    @NotNull(message = "{customerEmail.absent}")
    @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+" ,
            message = "{invalid.customerEmail.format}")
    private String customerEmailId;
    @Valid
    private Set<CartProductDetails> cartProducts;
}
