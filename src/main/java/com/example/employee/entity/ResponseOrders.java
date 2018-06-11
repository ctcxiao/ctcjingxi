package com.example.employee.entity;

import java.util.List;

public class ResponseOrders {
    private int id;
    private double totalPrice;
    private int userId;
    private String createTime;
    private List<Integer> purchaseItemList;

    public ResponseOrders(int id, double totalPrice, int userId, String createTime, List<Integer> purchaseItemList) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Integer> getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(List<Integer> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }
}
