package com.example.App.service;

import com.example.App.model.Product;
import com.example.App.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public void insertProduct(Product p){

        productsRepository.save(p);
    }

    public Product getProductById(String productId) {
        return productsRepository.findById(productId).orElse(null);
    }
}