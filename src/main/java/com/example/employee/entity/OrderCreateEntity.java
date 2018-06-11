package com.example.employee.entity;

public class OrderCreateEntity {
    private int productId;
    private int purchaseCount;

    public OrderCreateEntity(int productId, int purchaseCount) {
        this.productId = productId;
        this.purchaseCount = purchaseCount;
    }

    public OrderCreateEntity() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}
