package com.gokhansener.marketplacesearch.product.dto;

import com.gokhansener.marketplacesearch.product.model.ProductCategory;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductSearchRequest {

    @Size(max = 100, message = "Query must be most 100 characters")
    private String query;

    private ProductCategory category;

    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum price cannot be negative")
    private BigDecimal minPrice;

    @DecimalMin(value = "0.0", inclusive = true, message = "Maximum price cannot be negative")
    private BigDecimal maxPrice;

    private ProductSortOption sort = ProductSortOption.RELEVANCE;

    @Min(value = 0, message = "Page must be zero or greater")
    private int page = 0;

    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 100, message = "Size cannot be greater than 100")
    private int size = 20;

    @AssertTrue(message = "Minimum price must be less than or equal to maximum price")
    public boolean isPriceRangeValid() {
        if (minPrice == null || maxPrice == null) {
            return true;
        }

        return minPrice.compareTo(maxPrice) <= 0;
    }
}
