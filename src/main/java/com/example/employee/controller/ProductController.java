package com.example.employee.controller;

import com.example.employee.entity.Inventory;
import com.example.employee.entity.Product;
import com.example.employee.entity.ProductParam;
import com.example.employee.entity.ResponseProduct;
import com.example.employee.entity.UpdateProEntity;
import com.example.employee.repository.InventoryRepository;
import com.example.employee.repository.ProduceRepository;
import com.example.employee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProduceRepository produceRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ResponseProduct> createNewProduct(@RequestBody UpdateProEntity updateEntity) {
        ResponseProduct responseProduct = productService.createProductAndInventory(updateEntity);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "jingxi");
        return new ResponseEntity<>(responseProduct, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findProduct(ProductParam param) {
        List<Product> productList = productService.getProducts(param.getName(), param.getDescription());
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseProduct findOne(@PathVariable("id") int id) {
        Product product = produceRepository.findById(id).get();
        Inventory inventory = inventoryRepository.findByProductId(id);

        return new ResponseProduct(product.getId(), product.getName(), product.getDescription(),
            product.getPrice(), inventory);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") int id, @RequestBody UpdateProEntity updateEntity) {
        produceRepository.updateProduct(updateEntity.getName(), updateEntity.getDescription(), updateEntity.getPrice(), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
