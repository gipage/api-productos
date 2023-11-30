package com.paygoal.apiproductos.service.interfaces;

import com.paygoal.apiproductos.dto.CreateSuccessfullyDTO;
import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;
import com.paygoal.apiproductos.exceptions.ProductDoesNotExist;

import java.util.List;

public interface IProductService {
    CreateSuccessfullyDTO createProduct(ProductDTO productDTO) throws ApiException;
    ProductDTO updateProduct(Long id, ProductDTO productDTO) throws ApiException;
    ProductDTO getProduct(Long id) throws ApiException;
    void deleteProduct(Long id) throws ApiException;
    List<ProductDTO> getAll();
}
