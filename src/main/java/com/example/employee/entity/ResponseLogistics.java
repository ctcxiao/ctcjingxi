package com.example.employee.entity;

import java.time.LocalDateTime;

public class ResponseLogistics {

    private int id;

    private double totalPrice;
    private int userId;
    private LocalDateTime createTime;
    private String logisticsStatus;
    private String purchaseItemList;

    public ResponseLogistics(int id, double totalPrice, int userId, LocalDateTime createTime, String logisticsStatus, String purchaseItemList) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.createTime = createTime;
        this.logisticsStatus = logisticsStatus;
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

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(String purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }
}
