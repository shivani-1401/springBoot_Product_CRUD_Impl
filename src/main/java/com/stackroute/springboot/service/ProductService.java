package com.stackroute.springboot.service;

import com.stackroute.springboot.domain.Product;
import com.stackroute.springboot.exception.ProductAlreadyExistsException;
import com.stackroute.springboot.exception.ProductNotExistsException;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product) throws ProductAlreadyExistsException;

    public List<Product> getAllProducts();

    public Product getProductById(int prodId) throws ProductNotExistsException;

    public List<Product> getProductByCategory(String prodCategory) throws ProductNotExistsException;

    public void deleteProduct(int prodId) throws ProductNotExistsException;

    public Product updateProduct(Product product, int id) throws ProductNotExistsException;
}
