package com.stackroute.springboot.controller;

import com.stackroute.springboot.domain.Product;
import com.stackroute.springboot.exception.ProductAlreadyExistsException;
import com.stackroute.springboot.exception.ProductNotExistsException;
import com.stackroute.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productservice")
public class ProductController {

    private ProductService productService;
    private ResponseEntity responseEntity;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Create a new product
    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        try {
            Product createdProduct = productService.saveProduct(product);
            responseEntity = new ResponseEntity(createdProduct, HttpStatus.CREATED);
        } catch (ProductAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> productList = productService.getAllProducts();
            responseEntity = new ResponseEntity(productList, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        try {
            Product product = productService.getProductById(id);
            responseEntity = new ResponseEntity(product, HttpStatus.OK);
        } catch (ProductNotExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/products/category/{prodCategory}")
    public ResponseEntity<Product> getProductById(@PathVariable String prodCategory) {
        try {
            List<Product> products = productService.getProductByCategory(prodCategory);
            responseEntity = new ResponseEntity(products, HttpStatus.OK);
        } catch (ProductNotExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);

        }
        return responseEntity;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable int id) {
        try {
            Product product = productService.getProductById(id);
            productService.deleteProduct(id);
            responseEntity = new ResponseEntity(product, HttpStatus.OK);
        } catch (ProductNotExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id) {
        try {
            Product currentProduct = productService.getProductById(id);
            currentProduct = productService.updateProduct(product, id);
            responseEntity = new ResponseEntity(currentProduct, HttpStatus.OK);
        } catch (ProductNotExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
