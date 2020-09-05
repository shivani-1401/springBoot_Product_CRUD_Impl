package com.stackroute.springboot.repository;

import com.stackroute.springboot.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.prodCategory = ?1")
    public List<Product> findAllByCategory(String prodCategory);
}
