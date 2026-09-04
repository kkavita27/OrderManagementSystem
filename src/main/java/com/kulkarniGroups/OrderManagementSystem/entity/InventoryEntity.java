package com.kulkarniGroups.OrderManagementSystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;

    @NotNull
    private String inventoryProductName;

    @NotNull
    private Double inventoryLocation;

    @NotNull
    @ColumnDefault("0")
    private int inventoryQty;

//    @OneToMany(mappedBy = "inventory")
//    private List<OrderItem> orderItems = new ArrayList<>();
}
