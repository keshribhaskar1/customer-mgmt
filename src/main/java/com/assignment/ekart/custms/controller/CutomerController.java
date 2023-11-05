package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.service.CustomerService;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import static com.assignment.ekart.custms.config.Constant.DELETION_FAILED;
import static com.assignment.ekart.custms.config.Constant.FAILED_ADD_CUSTOMER;


@Slf4j
@CrossOrigin
@Validated
@RestController
@RequestMapping(value = "/customers")
public class CutomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/getCustomers")
    public ResponseEntity<List<CustomerDetails>> getCustomer() throws Exception {
        List<CustomerDetails> registeredCustomer = customerService.getCustomer();
        String status = registeredCustomer.get(0).getError();
        if(StringUtils.isEmpty(status)){
            return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(registeredCustomer, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getCustomers/{emailId}")
    public ResponseEntity<CustomerDetails> getCustomerByEmail(@PathVariable String emailId) throws Exception {
        CustomerDetails registeredCustomer = customerService.getCustomerByEmailId(emailId);
        String status = registeredCustomer.getError();
        if(StringUtils.isEmpty(status)){
            return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(registeredCustomer, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/addCustomers")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDetails customer) throws Exception {

        log.info("Registering customer with email id: " + customer.getEmailId());
        String registeredWithEmailID = customerService.addNewCustomer(customer);
        if(registeredWithEmailID.equalsIgnoreCase(FAILED_ADD_CUSTOMER)){
            return new ResponseEntity<>(registeredWithEmailID, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>(registeredWithEmailID, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/deleteCustomers/{ph_no}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("ph_no") @NotBlank @Size(max = 10,min=10)
                                                 @Pattern(regexp = "[0-9]+", message = "{\"Invalid phone number\"}")
                                                 String ph_no) throws Exception {

        String deleteStatus = customerService.deleteCustomer(ph_no);
        if(deleteStatus.equalsIgnoreCase(DELETION_FAILED)){
            return new ResponseEntity<>(deleteStatus, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(deleteStatus, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/ekart/add-product-kart")
    public ResponseEntity<String> addProductToCart(@Valid @RequestBody CustomerCartDetails customerCartDetails) throws Exception {
        ResponseEntity<String> productUpdate = customerService.updateProductsToKart(customerCartDetails);
        return productUpdate;
    }
}

