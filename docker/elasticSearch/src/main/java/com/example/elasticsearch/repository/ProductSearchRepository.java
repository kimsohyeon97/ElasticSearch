package com.example.elasticsearch.repository;

import com.example.elasticsearch.document.ProductDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository
        extends ElasticsearchRepository<ProductDocument, Long> {

    List<ProductDocument> findByProdNameContaining(String keyword, Pageable pageable);

    long countByProdNameContaining(String keyword);
}
