package com.mallmanagement.shopping_mall.controller;

import com.mallmanagement.shopping_mall.entity.Order;
import com.mallmanagement.shopping_mall.service.OrderService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController 
@RequestMapping("/api/v1/orders") 
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        
        Order createdOrder = orderService.createOrder(order);
        
        // Returns 200 OK
        String message = "Order created successfully with ID: " + createdOrder.getId();
        
        return new ResponseEntity<>(message, HttpStatus.OK); 
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders(); // HTTP Status 200 OK by default
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order); // HTTP Status 200 OK
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam String newStatus) {
        Order updatedOrder = orderService.updateOrderStatus(id, newStatus);
        return ResponseEntity.ok(updatedOrder); // HTTP Status 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
    	
    	orderService.deleteOrder(id);
     
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}