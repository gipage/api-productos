package com.paygoal.apiproductos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", updatable = false, nullable = false)
    private Long id;
    @Column(name = "name_product",nullable = false)
    private String name;
    @Column(name = "description_product",nullable = false)
    private String description;
    @Column(name = "price_product",nullable = false)
    private BigDecimal price;
    @Column(name = "quantity_product",nullable = false)
    private Integer quantity;
}
