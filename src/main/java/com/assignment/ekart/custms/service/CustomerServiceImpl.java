package com.assignment.ekart.custms.service;


import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CartProductDetails;
import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.model.ProductDetails;
import com.assignment.ekart.custms.repository.CustomerRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private RestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${application.product.name}")
    private String productApp;

    @Value("${application.kart.name}")
    private String kartApp;

    @Value("${application.customer.name}")
    private String customerApp;

    public String addNewCustomer(CustomerDetails customer) {
        try{
            CustomerDetailsEntity customerDetailsEntity = CustomerDetailsEntity.builder()
                    .name(customer.getName())
                    .address(customer.getAddress())
                    .phoneNumber(customer.getPhoneNumber())
                    .emailId(customer.getEmailId())
                    .build();
            customerRepo.save(customerDetailsEntity);
            Long id = customerDetailsEntity.getCustomerId();
            return "Successfully added customer with id: "+id;
        }catch (Exception e){
            return "Failed to add customer.";
        }
    }

    @Override
    public String deleteCustomer(String phoneNumber) {
        try{
            List<CustomerDetailsEntity> customerData = customerRepo.findByPhoneNumber(phoneNumber);
            if(customerData.isEmpty()){
                throw new SQLException("phone number " + phoneNumber +" not found");
            }else {
                customerRepo.deleteAll(customerData);
            }
            return "Successfully deleted customer with phone number: "+phoneNumber;
        }catch (Exception e){
            return "Failed to delete customer. Reason: "+e.getMessage();
        }
    }

    @Override
    public List<CustomerDetails> getCustomer() {
        List<CustomerDetailsEntity> cde = customerRepo.findAll();
        CustomerDetails cd = new CustomerDetails();
        List<CustomerDetails> cdl = new ArrayList<>();
        for(CustomerDetailsEntity cden:cde){
            cd.setName(cden.getName());
            cd.setAddress(cden.getAddress());
            cd.setPhoneNumber(cden.getPhoneNumber());
            cd.setEmailId(cden.getEmailId());
            cdl.add(cd);
        }
        return cdl;
    }

    @Override
    public CustomerDetails getCustomerByEmailId(String emailId) throws Exception {
        CustomerDetails cd = new CustomerDetails();
        CustomerDetailsEntity customer = null;
        Optional<CustomerDetailsEntity> cust = customerRepo.findByEmailId(emailId.toLowerCase());
        if(cust.isPresent()){
            customer = cust.get();
        }else{
            throw new Exception("CUSTOMER NOT FOUND");
        }
        cd.setEmailId(customer.getEmailId());
        cd.setName(customer.getName());
        cd.setAddress(customer.getAddress());
        cd.setPhoneNumber(customer.getPhoneNumber());
        return cd;
    }

    @Override
    public ResponseEntity<String> getProducts(CustomerCartDetails customerCartDetails) throws JsonProcessingException {
        Set<CartProductDetails> cartProductDetails = new HashSet<>();
        try {

            for (CartProductDetails cartProductDetail : customerCartDetails.getCartProducts()) {
                ResponseEntity<ProductDetails> response = template
                        .getForEntity("http://" + productApp + "/productApi/product/" + cartProductDetail.getProduct().getProductId(),
                                ProductDetails.class);
                ProductDetails productDetails = response.getBody();
                cartProductDetail.setProduct(productDetails);
                cartProductDetails.add(cartProductDetail);
            }

            customerCartDetails.setCartProducts(cartProductDetails);
            String cartDet = objectMapper.writeValueAsString(customerCartDetails);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<String>(cartDet, headers);

            ResponseEntity<String> isAdded = template.postForEntity("http://" + kartApp + "/kartApi/products", request, String.class);

            return isAdded;
        }catch (Exception e){
            ResponseEntity<String> error = new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
            return error;
        }
    }
}
