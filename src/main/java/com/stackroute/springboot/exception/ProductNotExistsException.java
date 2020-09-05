package com.stackroute.springboot.exception;

public class ProductNotExistsException extends Exception {

    public ProductNotExistsException(String msg) {
        super(msg);
    }
}
