package com.example.employee.entity;

import javax.persistence.*;

@Entity
@Table(name = "LogisticsRecords")
public class LogisticsRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalPrice;
    private int userId;
    private String createTime;
    private String logisticsStatus;
    private String purchaseString;

    public LogisticsRecords(int id, double totalPrice, int userId, String createTime, String logisticsStatus, String purchaseString) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.createTime = createTime;
        this.logisticsStatus = logisticsStatus;
        this.purchaseString = purchaseString;
    }

    public LogisticsRecords() {
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

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getPurchaseString() {
        return purchaseString;
    }

    public void setPurchaseString(String purchaseString) {
        this.purchaseString = purchaseString;
    }
}
