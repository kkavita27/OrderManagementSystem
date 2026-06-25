package com.kulkarniGroups.OrderManagementSystem.POJO;

public class OrderItemPOJO {

//1. Create OrderItemPOJO
//2. Add List<OrderItemPOJO> to OrderPOJO
//3. Update createOrder() conversion logic
//4. Test save
//5. Add RuntimeException
//6. Verify rollback

    private String productName;

    private Integer quantity;

    private Double price;

    // getters/setters

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    }

