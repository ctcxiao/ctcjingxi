package com.example.employee.repository;

import com.example.employee.entity.Orders;
import com.example.employee.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update orders set status=?1 where id=?2", nativeQuery = true)
    void updateOrderStatus(String status, int id);

    Orders findById(int id);

    List<Orders> findAllByUserId(int userid);
}
