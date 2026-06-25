package com.kulkarniGroups.OrderManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
public class OrderItem {

    @Id //import jakarta.persistence.Id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //GenerationType.IDENTITY tells the JPA provider (like Hibernate) to let the database handle the primary key generation using an auto-increment column.Instead of the Java application calculating the next ID, it sends the record to the database without one.
    private Long itemId;

    private String productName;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity order;

    public OrderItem() {
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    // getters and setters
}