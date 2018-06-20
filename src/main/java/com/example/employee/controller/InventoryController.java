package com.example.employee.controller;

import com.example.employee.entity.UpdateProEntity;
import com.example.employee.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @PutMapping(value = "/inventories/{id}")
    public ResponseEntity updateProductCount(@PathVariable("id") int id, @RequestBody UpdateProEntity updateProEntity) {
        inventoryRepository.updateCount(updateProEntity.getCount(), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
