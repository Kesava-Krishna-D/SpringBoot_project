package com.mallmanagement.shopping_mall.service;

import com.mallmanagement.shopping_mall.exception.ResourceNotFoundException;
import com.mallmanagement.shopping_mall.entity.Order;
import com.mallmanagement.shopping_mall.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal; 

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 1. Create a new Order (Includes new validation and BigDecimal math)
    public Order createOrder(Order order) {
        
        // --- Input Validation ---
        if (order.getSubTotalAmount() == null || order.getSubTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Subtotal amount must be positive.");
        }
        if (order.getCustomerEmail() == null || order.getShippingAddress() == null) {
            throw new IllegalArgumentException("Customer email and shipping address are required.");
        }
        
        // --- Total Amount Calculation using BigDecimal methods ---
        BigDecimal subtotal = order.getSubTotalAmount();
        BigDecimal tax = order.getTaxAmount() != null ? order.getTaxAmount() : BigDecimal.ZERO;
        BigDecimal shipping = order.getShippingCost() != null ? order.getShippingCost() : BigDecimal.ZERO;

        // Calculate the final total amount: subtotal + tax + shipping
        BigDecimal finalTotal = subtotal
            .add(tax)
            .add(shipping);

        order.setTotalAmount(finalTotal); // Set the calculated total

        // --- Initial Status ---
        order.setStatus("PENDING"); 
        
        return orderRepository.save(order);
    }

    // 2. Retrieve all Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 3. Retrieve Order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
    
    // 4. Update Order Status (Business rule enforcement)
    public Order updateOrderStatus(Long id, String newStatus) {
        Order order = getOrderById(id);
        
        // Business Rule: Cannot revert status from SHIPPED to PENDING
        if (order.getStatus().equals("SHIPPED") && newStatus.equals("PENDING")) {
             throw new IllegalArgumentException("Cannot revert shipped order to pending.");
        }
        
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
    
    // 5. Delete Order (Soft Delete Logic)
    // Note: This method is placed after the updateOrderStatus method.
    public void deleteOrder(Long id) {
    	Order order = getOrderById(id);
     
    	// BUSINESS RULE: Prevent deleting an order that is already SHIPPED or DELIVERED
    	if (order.getStatus().equals("SHIPPED") || order.getStatus().equals("DELIVERED")) {
    		throw new IllegalArgumentException("Cannot cancel an order that is already in transit or delivered.");
    	}
     
     // Perform the Soft Delete: Change the status
     order.setStatus("CANCELLED"); 
     orderRepository.save(order);
    }
}