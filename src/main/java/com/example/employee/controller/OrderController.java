package com.example.employee.controller;

import com.example.employee.entity.Order;
import com.example.employee.entity.OrderCreateEntity;
import com.example.employee.entity.ResponseOrders;
import com.example.employee.repository.OrderRepository;
import com.example.employee.service.OrderService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<List<Order>> createOrder(@RequestBody List<OrderCreateEntity> orderCreateEntities) {
        final List<Order> orderList = orderService.createOrders(orderCreateEntities);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("location", "jingxi");
        return new ResponseEntity<>(orderList, responseHeader, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity payForOrder(@PathVariable("id") int id, @RequestParam("orderStatus") String status) {
        orderService.updateOrderStatus(id, status);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseOrders findOrdersById(@PathVariable("id") int id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new RuntimeException("there is no order");
        }
        return new ResponseOrders(order.getId(), order.getTotalPrice(), order.getUserId(), order.getBuyTime(), order.getOrderDetail());
    }

    @GetMapping()
    public List<Order> findOrdersBuUserId(@RequestParam("userId") int id) {
        return orderRepository.findAllByUserId(id);
    }





}
