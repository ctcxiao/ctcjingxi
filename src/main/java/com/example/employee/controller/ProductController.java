package com.example.employee.controller;

import com.example.employee.entity.Products;
import com.example.employee.entity.ResponseProduct;
import com.example.employee.entity.UpdateProEntity;
import com.example.employee.repository.ProduceRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProduceRepository produceRepository;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<ResponseProduct> createNewProduct(@RequestBody String body) {
        UpdateProEntity updateEntity = new Gson().fromJson(body, UpdateProEntity.class);
        Products products = new Products(0, "",updateEntity.getName(), updateEntity.getDescription(), updateEntity.getPrice(), 0);
        String purchaseUserId = products.getUserId();
        List<Integer> purchaseItemList = new ArrayList<>();
        for (int i = 0; i < purchaseUserId.length(); i++) {
            purchaseItemList.add(purchaseUserId.charAt(i) - '0');
        }
        ResponseProduct responseProduct = new ResponseProduct(products.getId(), products.getName(), products.getDescription(),
                products.getPrice(), products.getCount(), purchaseItemList);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "jingxi");
        ResponseEntity<ResponseProduct> responseEntity = new ResponseEntity<>(responseProduct,responseHeaders, HttpStatus.CREATED);
        produceRepository.save(products);
        return responseEntity;

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
        return new ResponseProduct(products.getId(), products.getName(), products.getDescription(),
                products.getPrice(), products.getCount(), purchaseItemList);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable("id") int id, @RequestBody String body) {
        UpdateProEntity updateEntity = new Gson().fromJson(body, UpdateProEntity.class);
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
    public ResponseEntity updateProductCount(@PathVariable("id")int id, @RequestBody String body){
        UpdateProEntity updateProEntity = new Gson().fromJson(body, UpdateProEntity.class);
        produceRepository.updateProductCount(updateProEntity.getCount(), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
