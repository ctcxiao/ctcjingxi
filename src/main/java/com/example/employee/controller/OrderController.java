package com.example.employee.controller;

import com.example.employee.entity.Inventory;
import com.example.employee.entity.LogisticsRecords;
import com.example.employee.entity.OrderCreateEntity;
import com.example.employee.entity.Orders;
import com.example.employee.entity.ResponseLogistics;
import com.example.employee.entity.ResponseOrders;
import com.example.employee.repository.InventoryRepository;
import com.example.employee.repository.LogisticsRecordsRepository;
import com.example.employee.repository.OrderRepository;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LogisticsRecordsRepository logisticsRecordsRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<List<Orders>> createOrder(@RequestBody List<OrderCreateEntity> orderCreateEntities) {
        final List<Orders> ordersList = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderCreateEntities.forEach((orderCreateEntity) -> {
            String createTime = simpleDateFormat.format(System.currentTimeMillis());
            Orders orders = new Orders(0, "", orderCreateEntity.getPurchaseCount(),
                    createTime, 30.30, 1, "" + orderCreateEntity.getProductId());
            orderRepository.save(orders);

            int result = lockCount(orderCreateEntity.getProductId(), orderCreateEntity.getPurchaseCount());
            if (result == 0) {
                LogisticsRecords logisticsRecords = new LogisticsRecords(orders.getId(), orders.getTotalPrice(),
                        orders.getUserId(), orders.getBuyTime(), "", orders.getOrderDetail());
                logisticsRecordsRepository.save(logisticsRecords);
                ordersList.add(orders);
            }
        });

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("location", "jingxi");
        return new ResponseEntity<>(ordersList, responseHeader, HttpStatus.CREATED);
    }

    @Transactional
    int lockCount(int productId, int num){
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory == null){
            System.out.println("the products has no inventory!!");
            return -1;
        }

        if (inventory.getLockCount()+num>inventory.getCounts()){
            System.out.println("inventory is not enough!!");
            return -2;
        }
        inventoryRepository.updateLockCount(inventory.getLockCount()+num, productId);
        return 0;
    }

    @Transactional
    void unlockCount(int productId, int num){
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory == null){
            System.out.println("the products has no inventory!!");
            return;
        }
        inventoryRepository.updateLockCount(inventory.getLockCount()-num, productId);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity payForOrder(@PathVariable("id") int id, @RequestParam("orderStatus") String status) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = simpleDateFormat.format(System.currentTimeMillis());
        if ("withdrawn".equals(status)){
            withdrawOrder(id);
        }
        orderRepository.updateOrderStatus(status, createTime, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Transactional
    void withdrawOrder(int orderId) {
        Orders orders = orderRepository.findById(orderId);
        unlockCount(Integer.valueOf(orders.getOrderDetail()), orders.getBuyCount());
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseOrders findOrdersById(@PathVariable("id") int id) {
        Orders orders = orderRepository.findById(id);

        if (orders == null) {
            throw new RuntimeException("there is no orders");
        }
        String purchaseString = orders.getOrderDetail();
        List<Integer> purchaseItemList = new ArrayList<>();
        for (int i = 0; i < purchaseString.length(); i++) {
            purchaseItemList.add(purchaseString.charAt(i) - '0');
        }
        return new ResponseOrders(orders.getId(), orders.getTotalPrice(), orders.getUserId(), orders.getBuyTime(), purchaseItemList);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Orders> findOrdersBuUserId(@RequestParam("userId") int id) {
        return orderRepository.findAllByUserId(id);
    }

    @RequestMapping(value = "/logisticsRecords/{id}", method = RequestMethod.GET)
    public ResponseLogistics findLogistRecords(@PathVariable("id") int id) {
        LogisticsRecords logisticsRecords = logisticsRecordsRepository.findById(id).get();
        String purchaseString = logisticsRecords.getPurchaseString();
        List<Integer> purchaseItemList = new ArrayList<>();
        for (int i = 0; i < purchaseString.length(); i++) {
            purchaseItemList.add(purchaseString.charAt(i) - '0');
        }
        return new ResponseLogistics(logisticsRecords.getId(), logisticsRecords.getTotalPrice(), logisticsRecords.getUserId(), logisticsRecords.getCreateTime(),
                logisticsRecords.getLogisticsStatus(), purchaseItemList);
    }

    @RequestMapping(value = "/logisticsRecords/{id}/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateLogisticsStatus(@PathVariable("id") int id, @RequestParam("logisticsStatus") String logisticsStatus) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = simpleDateFormat.format(System.currentTimeMillis());
        if ("signed".equals(logisticsStatus)){
            signForLogistics(id);
        }
        logisticsRecordsRepository.updateLogisticsStatus(logisticsStatus, time, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Transactional
    void signForLogistics(int id) {
        Orders orders = orderRepository.findById(id);
        Inventory inventory = inventoryRepository.findByProductId(Integer.valueOf(orders.getOrderDetail()));
        inventoryRepository.updateCount(inventory.getCounts()-orders.getBuyCount(),Integer.valueOf(orders.getOrderDetail()));
        inventoryRepository.updateLockCount(inventory.getLockCount()-orders.getBuyCount(), Integer.valueOf(orders.getOrderDetail()));
    }

}
