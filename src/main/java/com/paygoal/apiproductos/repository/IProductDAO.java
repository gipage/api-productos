package com.paygoal.apiproductos.repository;

import com.paygoal.apiproductos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProductDAO extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> getAllOrderByPrice();
}
