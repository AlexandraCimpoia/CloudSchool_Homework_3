package com.example.App.service;

import com.example.App.model.*;
import com.example.App.repository.CustomerRepository;
import com.example.App.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customersRepository;

    @Autowired
    OrdersRepository ordersRepository;

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }
    public void createCustomer(Customer c) {
        customersRepository.save(c);
    }

    public void addProductsToOrder(Integer customerId, List<Product> products) {
        Customer customer = customersRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Order order = new Order();
            order.setCustomer(customer);
            order.setComments("No Comments");
            order.setOrderDate(LocalDate.now()); // Set order date as current date
            order.setShippedDate(LocalDate.now());
            order.setStatus("Shipped"); // Set initial status

            List<OrderDetails> orderDetailsList = new ArrayList<>();
            for (Product product : products) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrder(order);
                orderDetails.setProduct(product);
                orderDetails.setQuantity(1); // You can set the quantity as needed
                orderDetails.setPriceEach(product.getPrice());
                orderDetailsList.add(orderDetails);
            }

            order.setOrderDetails(orderDetailsList);
            ordersRepository.save(order);
        }
    }
//
//    public List<Order> getOrdersByUsername(String username) {
//        Customer customer = customersRepository.findByUsername(username);
//        if (customer != null) {
//            return customer.getOrders();
//        } else {
//            System.out.println("Customer not found for username: " + username);
//            return Collections.emptyList();
//        }
//    }
//
    public List<Order> getOrdersByCustomerId(Integer customerId) {
        Customer customer = customersRepository.findById(customerId).orElse(null);
        if (customer != null) {
            return customer.getOrders();
        } else {
            return Collections.emptyList();
        }
    }

    public boolean deleteCustomerByUsername(String username) {
        Customer customer = customersRepository.findByUsername(username);
        if (customer != null) {
            customersRepository.delete(customer);
            return true;
        }
        return false;
    }

    public boolean deleteCustomer(Integer customerId) {
        try {
            customersRepository.deleteById(customerId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public PostalDetailsCustomerDTO getPostalDetailsByUsername(String username) {
        Customer customer = customersRepository.findByUsername(username);
        if (customer != null) {
            PostalDetailsCustomerDTO postalDetails = new PostalDetailsCustomerDTO(customer);
            postalDetails.setAddress(customer.getAddress());
            postalDetails.setPhone(customer.getPhone());
            postalDetails.setCity(customer.getCity());

            return postalDetails;
        }
        return null;
    }
}
