package com.example.elasticsearch.repository;

import com.example.elasticsearch.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByLink(String link);
}