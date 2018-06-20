package com.example.employee.repository;

import com.example.employee.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update orders set status=?1, buyTime=?2 where id=?3", nativeQuery = true)
    void updateOrderStatus(String status, LocalDateTime time, int id);

    Order findById(int id);

    List<Order> findAllByUserId(int userId);
}
