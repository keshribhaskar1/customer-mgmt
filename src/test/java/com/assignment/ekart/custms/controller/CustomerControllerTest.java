package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.CustmsApplication;
import com.assignment.ekart.custms.model.CartProductDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.model.ProductDetails;
import com.assignment.ekart.custms.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.apache.logging.log4j.util.LambdaUtil.get;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@WebMvcTest(CustmsApplication.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private CustomerService customerService;

//    @Mock
//    private RestTemplate restTemplate;

    @Test
    public void getCustomerTest() throws Exception {
        List<CustomerDetails> customerDetails = new ArrayList<>();
        CustomerDetails customer = new CustomerDetails();
        customer.setName("John");
        customer.setAddress("USA");
        customer.setPhoneNumber("9854869784");
        customer.setEmailId("jus@gmail.com");
        customerDetails.add(customer);

        mvc.perform(MockMvcRequestBuilders.get("/getCustomers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("keshri"));

//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        when(customerService.getCustomer()).thenReturn(customerDetails);
//
//        HttpStatusCode expected = OK;
//        ResponseEntity<List<CustomerDetails>> responseEntity = customerController.getCustomer();
//        HttpStatusCode actual = responseEntity.getStatusCode();
//        System.out.println(responseEntity.getBody());
//        Assertions.assertEquals(expected,actual);
    }

//    @Test
//    public void addCustomerTest() throws Exception {
//        CustomerDetails customer = new CustomerDetails();
//        customer.setName("John");
//        customer.setAddress("USA");
//        customer.setPhoneNumber("9854869784");
//        customer.setEmailId("jus@gmail.com");
//
//        ResponseEntity<String> responseEntity = customerController.addCustomer(customer);
//        HttpStatusCode expected = OK;
//        HttpStatusCode actual = responseEntity.getStatusCode();
//        Assertions.assertEquals(expected,actual);
//    }
//    @Test
//    public void deleteCustomerTest() throws Exception {
//        String phone = "9840595543";
//        ResponseEntity<String> responseEntity = customerController.deleteCustomer(phone);
//        HttpStatusCode expected = OK;
//        HttpStatusCode actual = responseEntity.getStatusCode();
//        Assertions.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void addProductToCartTest() throws Exception {
//
//        CustomerDetails customer = new CustomerDetails();
//        customer.setName("John");
//        customer.setAddress("USA");
//        customer.setPhoneNumber("9854869784");
//        customer.setEmailId("test@gmail.com");
//        when(customerService.getCustomerByEmailId("test@gmail.com")).thenReturn(customer);
//        Set<CartProductDetails> cartProducts = new HashSet<>();
//        CartProductDetails obj = new CartProductDetails();
//        ProductDetails productDetails = new ProductDetails();
//        productDetails.setProductId(1);
//        obj.setProduct(productDetails);
//        cartProducts.add(obj);
//    }
}
