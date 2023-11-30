package com.paygoal.apiproductos.controller;

import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;
import com.paygoal.apiproductos.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
   @Autowired
    private IProductService productService;
   @PostMapping("/products")
   public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) throws ApiException {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
   }
}
