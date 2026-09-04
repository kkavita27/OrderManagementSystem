package com.kulkarniGroups.OrderManagementSystem.POJO;

import com.kulkarniGroups.OrderManagementSystem.entity.OrderEntity;

import java.time.LocalDate;
import java.util.List;

    public class OrderPOJO
{
//     Created a POJO for an entity Order
//     Difference between POJO and entity is, entity use @Entity annotation whereas OrderPOJO class is plain old java object pojo.
//    This POJO layer will talk to Controller layer.

    private Long orderId;

    private String orderName;

    private LocalDate orderDate;

    private Double orderPrice;

    private int orderQty;

    private String orderStatus;

    private List<OrderItemPOJO> orderItems;

    // getters and setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    }


