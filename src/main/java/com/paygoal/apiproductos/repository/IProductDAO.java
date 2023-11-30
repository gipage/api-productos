package com.paygoal.apiproductos.repository;

import com.paygoal.apiproductos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductDAO extends JpaRepository<Product, Long> {

}
