package com.example.employee.entity;

import java.util.List;

public class ResponseProduct {
    private int id;

    private String name;
    private String description;
    private double price;
    private int count;
    private List<Integer> inventory;

    public ResponseProduct(int id, String name, String description, double price, int count, List<Integer> inventory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.inventory = inventory;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Integer> getInventory() {
        return inventory;
    }

    public void setInventory(List<Integer> inventory) {
        this.inventory = inventory;
    }


}
