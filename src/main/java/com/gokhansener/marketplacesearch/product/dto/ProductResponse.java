package com.gokhansener.marketplacesearch.product.dto;

import com.gokhansener.marketplacesearch.product.model.Product;
import com.gokhansener.marketplacesearch.product.model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final ProductCategory category;
    private final BigDecimal price;
    private final int stock;
    private final double rating;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .rating(product.getRating())
                .build();
    }
}
