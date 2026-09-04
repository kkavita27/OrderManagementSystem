package com.kulkarniGroups.OrderManagementSystem.POJO;

import java.time.LocalDate;

public class InventoryPOJO {

    private Long inventoryId;

    private String inventoryProductName;

    private Double inventoryLocation;

    private int inventoryQty;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryProductName() {
        return inventoryProductName;
    }

    public void setInventoryProductName(String inventoryProductName) {
        this.inventoryProductName = inventoryProductName;
    }

    public Double getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(Double inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public int getInventoryQty() {
        return inventoryQty;
    }

    public void setInventoryQty(int inventoryQty) {
        this.inventoryQty = inventoryQty;
    }


}
