package com.kulkarniGroups.OrderManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Date;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @NotNull
    private String orderName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate;

    @NotNull(message = "Order status can not be null")
    private String orderStatus;

    @OneToOne
    @JsonIgnore
    public List<Orders> orders;

    public List<Orders> getOrders()
    {
        return  orders;
    }

    public Orders()
    {

    }

    public Orders(Long orderId, String orderName, LocalDate orderDate, String orderStatus, List<Orders> orders)
    {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
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

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
