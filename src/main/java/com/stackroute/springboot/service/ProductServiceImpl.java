package com.stackroute.springboot.service;

import com.stackroute.springboot.domain.Product;
import com.stackroute.springboot.exception.ProductAlreadyExistsException;
import com.stackroute.springboot.exception.ProductNotExistsException;
import com.stackroute.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) throws ProductAlreadyExistsException {
        Optional<Product> optional = productRepository.findById(product.getProdId());
        if (optional.isPresent()) {
            throw new ProductAlreadyExistsException("Product already exists");
        }
        Product createdProduct = productRepository.save(product);
        return createdProduct;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int prodId) throws ProductNotExistsException {
        Optional<Product> optional = productRepository.findById(prodId);
        if (!optional.isPresent()) {
            throw new ProductNotExistsException("Product with the given id doesn't exist");
        }
        return optional.get();
    }

    @Override
    public List<Product> getProductByCategory(String prodCategory) throws ProductNotExistsException {
        List<Product> list = productRepository.findAllByCategory(prodCategory);
        if (list.isEmpty()) {
            throw new ProductNotExistsException("Product with the given category doesn't exist");
        }
        return list;
    }

    @Override
    public void deleteProduct(int prodId) throws ProductNotExistsException {
        Optional optional = productRepository.findById(prodId);
        if (!optional.isPresent()) {
            throw new ProductNotExistsException("Product with the given id doesn't exist");
        }
        productRepository.deleteById(prodId);

    }

    @Override
    public Product updateProduct(Product product, int id) throws ProductNotExistsException {
        Optional optional = productRepository.findById(id);
        Product oldProduct;
        if (!optional.isPresent()) {
            throw new ProductNotExistsException("Product with the given id doesn't exist");
        } else {
            oldProduct = productRepository.findById(id).get();
            oldProduct.setProdName(product.getProdName());
            oldProduct.setProdCategory(product.getProdCategory());
        }
        return oldProduct;
    }

}
