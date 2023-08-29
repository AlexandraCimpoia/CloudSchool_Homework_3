package com.example.App.repository;

import com.example.App.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUsername(String username);

    Optional<Customer> findById(Integer customerId);
}
