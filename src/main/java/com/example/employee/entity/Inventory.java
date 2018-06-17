package com.example.employee.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int counts;
    private int lockCount;
    private int productId;

    public Inventory(int id, int counts, int lockCount, int productId) {
        this.id = id;
        this.counts = counts;
        this.lockCount = lockCount;
        this.productId = productId;
    }

    public Inventory() {
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getLockCount() {
        return lockCount;
    }

    public void setLockCount(int lockCount) {
        this.lockCount = lockCount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
