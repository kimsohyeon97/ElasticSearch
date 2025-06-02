package com.example.nori.service;

import com.example.nori.entity.Product;
import com.example.nori.entity.ProductEntity;
import com.example.nori.repository.ProductEntityRepository;
import com.example.nori.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductIndexingService {

    private final ProductEntityRepository productEntityRepository;
    private final ProductRepository productRepository;

    public void indexAllProducts() {
        List<ProductEntity> mysqlProducts = productEntityRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Product> esProducts = mysqlProducts.stream()
                .map(entity -> {
                    Product p = new Product();
                    p.setId(String.valueOf(entity.getId()));
                    p.setProductName(entity.getProduct_name());
                    p.setMallName(entity.getMall_name());
                    p.setKeyword(entity.getKeyword());
                    p.setPrice(entity.getPrice());
                    p.setLink(entity.getLink());

                    try {
                        String crawledAtStr = entity.getCrawled_at();
                        if (crawledAtStr != null && !crawledAtStr.isEmpty()) {
                            p.setCrawledAt(formatter.parse(crawledAtStr));
                        } else {
                            p.setCrawledAt(null);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        p.setCrawledAt(null);
                    }

                    return p;
                })
                .toList();

        productRepository.saveAll(esProducts);
        log.info("Elasticsearch에 {}개의 상품 데이터를 색인 완료했습니다.", esProducts.size());
    }
}
