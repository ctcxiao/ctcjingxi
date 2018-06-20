package com.example.employee.service;

import com.example.employee.entity.Inventory;
import com.example.employee.entity.Product;
import com.example.employee.entity.ResponseProduct;
import com.example.employee.entity.UpdateProEntity;
import com.example.employee.repository.InventoryRepository;
import com.example.employee.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProduceRepository produceRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public ResponseProduct createProductAndInventory(@RequestBody UpdateProEntity updateEntity) {
        Product product = new Product("", updateEntity.getName(), updateEntity.getDescription(), updateEntity.getPrice());

        saveProducts(product);

        Inventory inventory = createInventory(product);
        inventoryRepository.save(inventory);

        return new ResponseProduct(product.getId(), product.getName(), product.getDescription(),
            product.getPrice(), inventory);
    }

    @Transactional
    void saveProducts(Product product) {
        produceRepository.save(product);
    }

    @Transactional
    Inventory createInventory(Product products) {
        List<Product> product = produceRepository.findAllByName(products.getName());
        return new Inventory(0, 100, 0, product.get(product.size() - 1).getId());
    }

    public List<Product> getProducts(String name, String description) {
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(description)) {
            return produceRepository.findAll();
        }

        if (!StringUtils.isEmpty(name) && StringUtils.isEmpty(description)) {
            return produceRepository.findAllByName(name);
        }

        return produceRepository.findByNameLikeAndDescriptionLike(getLikeString(name), getLikeString(description));
    }

    private String getLikeString(String description) {
        return "%" + description + "%";
    }
}
