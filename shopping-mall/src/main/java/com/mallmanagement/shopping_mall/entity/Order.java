package com.mallmanagement.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.Data; 
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity 
@Table(name = "orders") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Order {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "order_id")
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "shipping_address", length = 255)
    private String shippingAddress;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now(); 

    @Column(name = "subtotal_amount", precision = 10, scale = 2)
    private BigDecimal subTotalAmount;

    @Column(name = "tax_amount", precision = 10, scale = 2)
    private BigDecimal taxAmount;

    @Column(name = "shipping_cost", precision = 10, scale = 2)
    private BigDecimal shippingCost;
    
    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount; 
    
    @Column(name = "current_status", nullable = false, length = 20)
    private String status; 

    @Column(name = "payment_method", length = 50)
    private String paymentMethod; 
}