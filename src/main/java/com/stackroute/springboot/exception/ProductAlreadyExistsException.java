package com.stackroute.springboot.exception;

public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException(String msg) {
        super(msg);
    }

}
