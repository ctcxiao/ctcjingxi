package com.example.employee.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Products")
public class Products implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double price;

    private String userId;

    public Products(int id, String userId, String name, String description, double price) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public Products() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
