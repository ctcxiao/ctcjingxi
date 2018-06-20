package com.example.employee.entity;

import java.time.LocalDateTime;

public class ResponseOrders {
    private int id;
    private double totalPrice;
    private int userId;
    private LocalDateTime createTime;
    private String purchaseItemList;

    public ResponseOrders(int id, double totalPrice, int userId, LocalDateTime createTime, String purchaseItemList) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.createTime = createTime;
        this.purchaseItemList = purchaseItemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(String purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }
}
