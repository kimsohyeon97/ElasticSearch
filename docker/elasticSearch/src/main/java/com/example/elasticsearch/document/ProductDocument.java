package com.example.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@Document(indexName = "product_20240604")
public class ProductDocument {
    @Id
    private Long id;

    @Field(type = FieldType.Text,
            analyzer = "autocomplete_analyzer",
            searchAnalyzer = "standard")
    private String prodName;

    @Field(type = FieldType.Keyword)
    private String company;

    @Field(type = FieldType.Float)
    private Double ratingAvg;

    @Field(type = FieldType.Integer)
    private Integer reviewCount;

    @Field(type = FieldType.Integer)
    private Integer prodPrice;

    @Field(type = FieldType.Keyword)
    private String snameList;

    @Field(type = FieldType.Keyword)
    private String link;

    @Field(type = FieldType.Integer)
    private Integer salesCount;

    @Field(type = FieldType.Date)
    private LocalDateTime crawledAt;
}
