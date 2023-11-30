package com.paygoal.apiproductos.controller;

import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;
import com.paygoal.apiproductos.repository.IProductDAO;
import com.paygoal.apiproductos.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final IProductService productService;
    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;

    }
   @PostMapping("/products")
   public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) throws ApiException {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
   }
   @PutMapping("/products/{id}")
   public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO) throws ApiException {
       return new ResponseEntity<>(productService.updateProduct(id,productDTO), HttpStatus.OK);
   }
   @GetMapping("/products/{id}")
   public ResponseEntity<?> getProduct(@PathVariable long id) throws ApiException {
       return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
   }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ApiException {
       productService.deleteProduct(id);
       return ResponseEntity.noContent().build();

    }
    @GetMapping("/products")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
}
