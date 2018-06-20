package com.example.employee.service;

import com.example.employee.entity.Inventory;
import com.example.employee.entity.LogisticsRecords;
import com.example.employee.entity.Order;
import com.example.employee.entity.OrderCreateEntity;
import com.example.employee.repository.InventoryRepository;
import com.example.employee.repository.LogisticsRecordsRepository;
import com.example.employee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LogisticsRecordsRepository logisticsRecordsRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Order> createOrders(@RequestBody List<OrderCreateEntity> orderCreateEntities) {
        final List<Order> orderList = new ArrayList<>();

        orderCreateEntities.forEach(orderCreateEntity -> parseCreateEntityToOrder(orderList, orderCreateEntity));

        return orderList;
    }

    private void parseCreateEntityToOrder(List<Order> orderList, OrderCreateEntity orderCreateEntity) {
        Order order = new Order(0, "", orderCreateEntity.getPurchaseCount(),
            LocalDateTime.now(), 30.30, 1, "" + orderCreateEntity.getProductId());
        orderRepository.save(order);

        int result = lockCount(orderCreateEntity.getProductId(), orderCreateEntity.getPurchaseCount());
        if (result == 0) {
            LogisticsRecords logisticsRecords = new LogisticsRecords(order.getId(), order.getTotalPrice(),
                order.getUserId(), order.getBuyTime(), "", order.getOrderDetail());
            logisticsRecordsRepository.save(logisticsRecords);
            orderList.add(order);
        }
    }

    public void updateOrderStatus( int id, String status) {
        if ("withdrawn".equals(status)) {
            withdrawOrder(id);
        }
        orderRepository.updateOrderStatus(status, LocalDateTime.now(), id);
    }

    @Transactional
    int lockCount(int productId, int num) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory == null) {
            System.out.println("the products has no inventory!!");
            return -1;
        }

        if (inventory.getLockCount() + num > inventory.getCount()) {
            System.out.println("inventory is not enough!!");
            return -2;
        }
        inventoryRepository.updateLockCount(inventory.getLockCount() + num, productId);
        return 0;
    }

    @Transactional
    void unlockCount(int productId, int num) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory == null) {
            System.out.println("the products has no inventory!!");
            return;
        }
        inventoryRepository.updateLockCount(inventory.getLockCount() - num, productId);
    }

    @Transactional
    void withdrawOrder(int orderId) {
        Order order = orderRepository.findById(orderId);
        unlockCount(Integer.valueOf(order.getOrderDetail()), order.getBuyCount());
    }


    @Transactional
    public void signForLogistics(int id) {
        Order order = orderRepository.findById(id);
        Inventory inventory = inventoryRepository.findByProductId(Integer.valueOf(order.getOrderDetail()));
        inventoryRepository.updateCount(inventory.getCount() - order.getBuyCount(), Integer.valueOf(order.getOrderDetail()));
        inventoryRepository.updateLockCount(inventory.getLockCount() - order.getBuyCount(), Integer.valueOf(order.getOrderDetail()));
    }
}
