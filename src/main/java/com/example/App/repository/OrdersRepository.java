package com.example.App.repository;

import com.example.App.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    default List<Order> findByCustomerId(Integer customerId) {
        return null;
    }
}
