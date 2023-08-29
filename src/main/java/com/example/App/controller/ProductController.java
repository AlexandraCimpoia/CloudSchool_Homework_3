package com.example.App.controller;


import com.example.App.model.Product;
import com.example.App.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Random;

@Controller
public class ProductController {
    @Autowired
    ProductService productsService;

    //create an endpoint which displays all products
    //http://localhost:8080/getAllProducts -> returns 10 products
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productsService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //create 10 products in the database
    //http://localhost:8080/createProducts -> returns code 500 but works :)
    @PostMapping(value = "/createProducts")
    public void createProducts() {
        String[] productNames = {"Apple", "Bread", "Milk", "Cheese", "Eggs", "Orange", "Yogurt", "Cereal", "Banana", "Juice"};
        Random random = new Random();

        for (String productName : productNames) {
            Product p = new Product();
            p.setName(productName);
            p.setStock(random.nextInt(50) + 1);
            p.setDescription("Description for " + productName);
            p.setPrice(random.nextDouble() * 10 + 1);
            productsService.insertProduct(p);
        }
    }
}
