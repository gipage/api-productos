package com.paygoal.apiproductos.exceptions;

import org.springframework.http.HttpStatus;

public class ProductDoesNotExist extends ApiException{
    public ProductDoesNotExist(long id) {
        super("Product with id "+id+" does not exist", HttpStatus.NOT_FOUND);
    }
}
