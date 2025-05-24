package com.example.nori.repository;

import com.example.nori.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ProductRepository extends ElasticsearchRepository<Product, String> {
}
