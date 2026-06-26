package com.gokhansener.marketplacesearch.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductSearchResponse {

    private final List<ProductResponse> items;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
}
