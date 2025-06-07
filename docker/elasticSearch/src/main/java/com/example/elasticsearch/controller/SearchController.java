package com.example.elasticsearch.controller;

import com.example.elasticsearch.document.ProductDocument;
import com.example.elasticsearch.dto.PageRequestDTO;
import com.example.elasticsearch.dto.PageResponseDTO;
import com.example.elasticsearch.dto.ProductDTO;
import com.example.elasticsearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /** 1) 검색 페이지 진입 (뷰만) */
    @GetMapping("/search")
    public String search() {
        return "/searchList";
    }


    /** 2) Ajax용 데이터 페칭 */
    @GetMapping("/ajaxSearchList")
    @ResponseBody
    public PageResponseDTO<ProductDTO> ajaxSearchList(
            PageRequestDTO pageRequestDTO,
            @RequestParam(value = "view", defaultValue = "list") String view
    ) {
        // ES에서 페이징된 결과 조회
        List<ProductDocument> docs = searchService.search(
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPg(),
                pageRequestDTO.getSize(),
                pageRequestDTO.getSortType(),
                pageRequestDTO.getPeriod()
        );
        long totalHits = searchService.count(pageRequestDTO.getKeyword(), pageRequestDTO.getPeriod());

        // Document → DTO 변환
        List<ProductDTO> dtos = docs.stream()
                .map(ProductDTO::fromDocument)
                .toList();

        // PageResponseDTO 생성 (builder 대신 생성자 + setter)
        PageResponseDTO<ProductDTO> response =
                new PageResponseDTO<>(pageRequestDTO, dtos, (int) totalHits);

        response.setView(view);
        response.setSortType(pageRequestDTO.getSortType());
        response.setPeriod(pageRequestDTO.getPeriod());

        return response;
    }
}

