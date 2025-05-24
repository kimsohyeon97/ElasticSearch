package com.example.nori.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.Date;

@Document(indexName = "product_search_index")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    @Field(name = "product_name", type = FieldType.Text, analyzer = "nori")
    private String productName;

    @Field(name = "mall_name", type = FieldType.Keyword)
    private String mallName;

    @Field(name = "keyword", type = FieldType.Text, analyzer = "nori")
    private String keyword;

    @Field(type = FieldType.Integer)
    private int price;

    @Field(name = "link", type = FieldType.Keyword)
    private String link;

    @Field(type = FieldType.Date)
    private Date crawledAt;
}
