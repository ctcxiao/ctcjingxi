package com.example.employee.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private String status;

    private int buyCount;
    private String buyTime;
    private double totalPrice;
    private String orderDetail;


    public Orders(int id, String status, int buyCount, String buyTime, double totalPrice, int userId, String orderDetail) {
        this.id = id;
        this.status = status;
        this.buyCount = buyCount;
        this.buyTime = buyTime;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.orderDetail = orderDetail;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Orders() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
