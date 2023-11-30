package com.paygoal.apiproductos.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ProductAlreadyExist extends ApiException {
    public ProductAlreadyExist(long id, String name) {
        super("Product with id " + id + " and name " + name + " already exist", HttpStatus.CONFLICT);
    }
}
