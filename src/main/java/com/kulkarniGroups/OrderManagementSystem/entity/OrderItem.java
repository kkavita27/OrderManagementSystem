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

    /* LAZY vs EAGER
    Then, if a particular service method needs the items immediately,
    create a repository method using JOIN FETCH rather than changing the entire relationship to EAGER.
    This gives you better performance and more control over when related data is loaded.
     */
    /*
        Summary
        Layer	        Responsibility
        Entity	        Defines the fetch strategy (FetchType.LAZY or FetchType.EAGER)
        Repository	    Retrieves entities and can override fetch behavior for specific queries (e.g., JOIN FETCH)
        Service	        Uses the entities and may trigger lazy loading by accessing related fields within a transaction
     */
    @ManyToOne(fetch = FetchType.LAZY) // Defines the fetch strategy
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

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