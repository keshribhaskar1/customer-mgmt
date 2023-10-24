package com.assignment.ekart.custms.service;

import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CartProductDetails;
import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.model.ProductDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    public String addNewCustomer(CustomerDetails customer);
    public String deleteCustomer(String phoneNumber);

    public List<CustomerDetails> getCustomer();

    public CustomerDetails getCustomerByEmailId(String emailId) throws Exception;

    public ResponseEntity<String> getProducts(CustomerCartDetails customerCartDetails) throws JsonProcessingException;
}
