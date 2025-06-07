package com.example.elasticsearch.service;

import com.example.elasticsearch.document.ProductDocument;
import com.example.elasticsearch.repository.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ProductSearchRepository searchRepo;

    public List<ProductDocument> search(String keyword, int pg, int size, String sortType, String period) {
        return searchRepo.findByProdNameContaining(keyword, PageRequest.of(0, 20));
    }

    public long count(String keyword, String period) {
        // period 무시하거나, 필요 시 로직 분기
        return searchRepo.countByProdNameContaining(keyword);
    }
}
