package com.mallmanagement.shopping_mall.repository;

import com.mallmanagement.shopping_mall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Terminologies: Marks this as a data access object
// Terminologies: JpaRepository provides ready-to-use CRUD methods (save, findAll, etc.)
// <Entity Class, Primary Key Type>
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods can be added here, e.g., 
    // List<Order> findByStatus(String status);
}