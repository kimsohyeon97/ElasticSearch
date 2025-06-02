package com.example.nori.controller;

import com.example.nori.service.ProductIndexingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductIndexingService productIndexingService;

    @GetMapping("/index-all")
    public String indexAllProducts() {
        productIndexingService.indexAllProducts();
        return "MySQL 데이터 -> Elasticsearch 색인 완료";
    }
}
