package com.assignment.ekart.custms.service;

import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    public String addNewCustomer(CustomerDetails customer);
    public String deleteCustomer(String phoneNumber);

    public List<CustomerDetails> getCustomer();

    public CustomerDetails getCustomerByEmailId(String emailId) throws Exception;

    public ResponseEntity<String> updateProductsToKart(CustomerCartDetails customerCartDetails) throws JsonProcessingException;
}
