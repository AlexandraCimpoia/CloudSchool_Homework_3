package com.example.App.controller;

import com.example.App.model.Customer;
import com.example.App.model.Order;
import com.example.App.model.PostalDetailsCustomerDTO;
import com.example.App.model.Product;
import com.example.App.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customersService;

    //http://localhost:8080/getAllCustomers -> returns 6 customers
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customersService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    //create a new customer
    //http://localhost:8080/createCustomer -> returns code 500 but works :)
    @PostMapping(value = "/createCustomer")
    public void createCustomer(){
        Customer c = new Customer();
        c.setUsername("vasile");
        c.setFirstName("Vasile");
        c.setLastName("Popa");
        c.setPhone("0763419758");
        c.setAddress("Bd. Unirii");
        c.setCity("Bucuresti");
        c.setPostalCode("123456");
        c.setCountry("Romania");
        try{
        customersService.createCustomer(c);}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Create an endpoint which will return the postal details of an user, given his username as input
    //http://localhost:8080/getPostalDetailsByUsername/jane_smith
    @GetMapping("/getPostalDetailsByUsername/{username}")
    public ResponseEntity<PostalDetailsCustomerDTO> getPostalDetailsByUsername(@PathVariable String username) {
        PostalDetailsCustomerDTO postalDetails = customersService.getPostalDetailsByUsername(username);
        if (postalDetails != null) {
            return ResponseEntity.ok(postalDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //add 5 products to an order belonging to the customer (the products are sent as a request body in postman. you can get the products by calling the getAllProducts endpoint)
    //http://localhost:8080/addProductsToOrder/4 -> returns "Products added to order successfully.")
    @PostMapping("/addProductsToOrder/{customerId}")
    public ResponseEntity<String> addProductsToOrder(@PathVariable Integer customerId,
                                                     @RequestBody List<Product> products) {
        customersService.addProductsToOrder(customerId, products);
        return ResponseEntity.ok("Products added to order successfully.");
    }

    //create an endpoint that can display all orders belonging to a customer
    //http://localhost:8080/getOrdersByCustomerId/4 -> returns 2 orders
    //customer with id 4 and 6 are the only ones that have orders
    @GetMapping("/getOrdersByCustomerId/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Integer customerId) {
        List<Order> orders = customersService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
//
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) {
        boolean deleted = customersService.deleteCustomer(customerId);
        if (deleted) {
            return ResponseEntity.ok("Customer deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteCustomerByUsername/{username}")
    public ResponseEntity<String> deleteCustomerByUsername(@PathVariable String username) {
        boolean deleted = customersService.deleteCustomerByUsername(username);
        if (deleted) {
            return ResponseEntity.ok("Customer deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
