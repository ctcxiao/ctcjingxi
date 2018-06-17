package com.example.employee.entity;

import java.util.List;

public class ResponseProduct {
    private int id;

    private String name;
    private String description;
    private double price;

    private List<Integer> purchaseItemList;

    private Inventory inventory;

    public ResponseProduct(int id, String name, String description, double price, List<Integer>purchaseItemList, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.purchaseItemList = purchaseItemList;
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

    public List<Integer> getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(List<Integer> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
