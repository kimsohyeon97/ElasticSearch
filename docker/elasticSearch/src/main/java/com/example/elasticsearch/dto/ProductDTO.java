package com.example.elasticsearch.dto;

import com.example.elasticsearch.document.ProductDocument;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String prodName;
    private String company;
    private Double ratingAvg;
    private Integer reviewCount;
    private Integer prodPrice;
    private String snameList;
    private String link;
    private Integer salesCount;
    private String crawledAt;

    /** Elasticsearch Document → DTO 변환 */
    public static ProductDTO fromDocument(ProductDocument doc) {
        return ProductDTO.builder()
                .id(doc.getId())
                .prodName(doc.getProdName())
                .company(doc.getCompany())        // ← here
                .ratingAvg(doc.getRatingAvg())
                .reviewCount(doc.getReviewCount())
                .prodPrice(doc.getProdPrice())
                .snameList(doc.getSnameList())
                .link(doc.getLink())
                .salesCount(doc.getSalesCount())
                .crawledAt(doc.getCrawledAt() != null
                        ? doc.getCrawledAt().toString()
                        : null)
                .build();
    }
}
