package com.kulkarniGroups.OrderManagementSystem.POJO;

import com.kulkarniGroups.OrderManagementSystem.entity.OrderEntity;

import java.time.LocalDate;
import java.util.List;

    public class OrderPOJO
{
//     Created a POJO for a entity Order
//     Difference between POJO and entity is, entity use @Entity annotation whereas OrderPOJO class is plain java pojo.

//    This POJO layer will talk to Controller layer.

    private Long orderId;
    private String orderName;
    private LocalDate orderDate;
    private Double orderPrice;
    private int orderQty;
    private String orderStatus;

    public List<OrderEntity> orders;

    public OrderPOJO() {}
    public List<OrderEntity> getOrders()
    {
        return  orders;
    }

    public void OrderEntity(Long orderId, String orderName, LocalDate orderDate, String orderStatus, int orderQty, Double orderPrice, List<OrderEntity> orders)
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
