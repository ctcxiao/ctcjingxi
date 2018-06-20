package com.example.employee.service;

import com.example.employee.repository.InventoryRepository;
import com.example.employee.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018/6/20.
 */
public class ProductService {
    @Autowired
    private ProduceRepository produceRepository;

    @Autowired
    private InventoryRepository inventoryRepository;
}
