package com.kulkarniGroups.OrderManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @NotNull
    private String orderName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate;

    @NotNull(message = "Order status can not be null")
    private String orderStatus;

    @NotNull
    private Double orderPrice;

    @NotNull
    private int orderQty;

    @OneToOne
    @JsonIgnore
    public List<OrderEntity> orders;

    public List<OrderEntity> getOrders()
    {
        return  orders;
    }

    public OrderEntity()
    {

    }

    public OrderEntity(Long orderId, String orderName, LocalDate orderDate, String orderStatus, int orderQty, Double orderPrice, List<OrderEntity> orders)
    {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderPrice = orderPrice;
        this.orderQty = orderQty;
        this.orders = orders;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }
}
