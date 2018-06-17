package com.example.employee.repository;

import com.example.employee.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Modifying
    @Transactional
    @Query("update Inventory set lockCount=?1 where productId=?2")
    void updateLockCount(int lockCount, int id);

    @Modifying
    @Transactional
    @Query("update Inventory set counts=?1 where productId=?2")
    void updateCount(int count, int id);

    @Query(value = "select * from Inventory where productId=?1", nativeQuery = true)
    Inventory findByProductId(int productId);
}
