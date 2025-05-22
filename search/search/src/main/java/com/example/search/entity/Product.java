package com.example.search.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    @Column(name = "product_name")
    private String productName;

    private Integer price;

    @Column(unique = true, length = 500)
    private String link;

    @Column(name = "mall_name")
    private String mallName;

    @Column(name = "crawled_at")
    private LocalDateTime crawledAt = LocalDateTime.now();
}
