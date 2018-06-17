package com.example.employee.repository;

import com.example.employee.entity.Products;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProduceRepository extends JpaRepository<Products, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update products set name=?1, description=?2, price=?3 where id=?4", nativeQuery = true)
    int updateProduct(String newName,String description, double price, int id);

    Products findByName(String name);

    @Query(value = "select * from products where name=?1 and description like %?2%", nativeQuery = true)
    Products findByDescriptionLikeAndName(String name, String description);

    @Modifying
    @Transactional
    @Query(value = "update Products set count=?1 where id=?2", nativeQuery = true)
    void updateProductCount(int count, int id);


    List<Products> findAllByName(String name);
}
