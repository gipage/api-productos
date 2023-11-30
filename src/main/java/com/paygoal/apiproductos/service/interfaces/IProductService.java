package com.paygoal.apiproductos.service.interfaces;

import com.paygoal.apiproductos.dto.CreateSuccessfullyDTO;
import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;

public interface IProductService {
    CreateSuccessfullyDTO createProduct(ProductDTO productDTO) throws ApiException;
    ProductDTO updateProduct(long id, ProductDTO productDTO) throws ApiException;
    ProductDTO getProduct(long id) throws ApiException;
}
