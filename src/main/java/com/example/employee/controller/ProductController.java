package com.example.employee.controller;

import com.example.employee.entity.Inventory;
import com.example.employee.entity.Products;
import com.example.employee.entity.ResponseProduct;
import com.example.employee.entity.UpdateProEntity;
import com.example.employee.repository.InventoryRepository;
import com.example.employee.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProduceRepository produceRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<ResponseProduct> createNewProduct(@RequestBody UpdateProEntity updateEntity) {
        Products products = new Products(0, "", updateEntity.getName(), updateEntity.getDescription(), updateEntity.getPrice());

        String purchaseUserId = products.getUserId();
        List<Integer> purchaseItemList = new ArrayList<>();
        for (int i = 0; i < purchaseUserId.length(); i++) {
            purchaseItemList.add(purchaseUserId.charAt(i) - '0');
        }

        saveProducts(products);

        Inventory inventory = createInventory(products);
        inventoryRepository.save(inventory);

        ResponseProduct responseProduct = new ResponseProduct(products.getId(), products.getName(), products.getDescription(),
                products.getPrice(), purchaseItemList, inventory);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "jingxi");
        return new ResponseEntity<>(responseProduct, responseHeaders, HttpStatus.CREATED);
    }

    @Transactional
    void saveProducts(Products products) {
        produceRepository.save(products);
    }

    @Transactional
    Inventory createInventory(Products products) {
        List<Products> product = produceRepository.findAllByName(products.getName());
        return new Inventory(0, 100, 0, product.get(product.size()-1).getId());
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Products> findAllProducts() {
        return produceRepository.findAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseProduct findOne(@PathVariable("id") int id) {
        Products products = produceRepository.findById(id).get();
        String purchaseUserId = products.getUserId();
        List<Integer> purchaseItemList = new ArrayList<>();
        for (int i = 0; i < purchaseUserId.length(); i++) {
            purchaseItemList.add(purchaseUserId.charAt(i) - '0');
        }
        Inventory inventory = inventoryRepository.findByProductId(id);
        return new ResponseProduct(products.getId(), products.getName(), products.getDescription(),
                products.getPrice(), purchaseItemList, inventory);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable("id") int id, @RequestBody UpdateProEntity updateEntity) {
        produceRepository.updateProduct(updateEntity.getName(), updateEntity.getDescription(), updateEntity.getPrice(), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, params = "name")
    public ResponseEntity<List<Products>> findProductByName(@RequestParam("name") String name) {
        List<Products> productsList = new ArrayList<>();
        productsList.add(produceRepository.findByName(name));
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, params = {"name", "description"})
    public ResponseEntity<List<Products>> findProductByName(@RequestParam("name") String name, @RequestParam("description") String description) {
        List<Products> productsList = new ArrayList<>();
        productsList.add(produceRepository.findByDescriptionLikeAndName(name, description));
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/inventories/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProductCount(@PathVariable("id") int id, @RequestBody UpdateProEntity updateProEntity) {
        inventoryRepository.updateCount(updateProEntity.getCount(), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
