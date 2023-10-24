package com.assignment.ekart.custms.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDetails {

    private String name;
    @NotNull(message = "{email.absent}")
    @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+",
            message = "{invalid.email.format}")
    private String emailId;
    @Size(max=10, min = 10, message = "{customer.invalid.phoneNumber}")
    @Pattern(regexp = "[0-9]+", message = "{customer.invalid.phoneNumber}")
    private String phoneNumber;
    @NotNull(message = "{customer.address.absent}")
    private String address;
}
