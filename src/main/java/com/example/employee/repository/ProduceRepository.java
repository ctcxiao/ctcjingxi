package com.example.employee.repository;

import com.example.employee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProduceRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update products set name=?1, description=?2, price=?3 where id=?4", nativeQuery = true)
    int updateProduct(String newName,String description, double price, int id);

    List<Product> findByNameLikeAndDescriptionLike(String name, String description);

    List<Product> findAllByName(String name);
}
