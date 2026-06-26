package com.gokhansener.marketplacesearch.product.service;

import com.gokhansener.marketplacesearch.product.dto.ProductResponse;
import com.gokhansener.marketplacesearch.product.dto.ProductSearchRequest;
import com.gokhansener.marketplacesearch.product.dto.ProductSearchResponse;
import com.gokhansener.marketplacesearch.product.dto.ProductSortOption;
import com.gokhansener.marketplacesearch.product.model.Product;
import com.gokhansener.marketplacesearch.product.model.ProductCategory;
import com.gokhansener.marketplacesearch.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductSearchResponse searchProducts(ProductSearchRequest request) {
        List<Product> filteredProducts = productRepository.findAll()
                .stream()
                .filter(product -> matchesQuery(product, request.getQuery()))
                .filter(product -> matchesCategory(product, request.getCategory()))
                .filter(product -> matchesMinPrice(product, request.getMinPrice()))
                .filter(product -> matchesMaxPrice(product, request.getMaxPrice()))
                .sorted(getComparator(request.getSort()))
                .toList();

        int totalElements = filteredProducts.size();
        int totalPages = calculateTotalPages(totalElements, request.getSize());

        int fromIndex = Math.min(request.getPage() * request.getSize(), totalElements);
        int toIndex = Math.min(fromIndex + request.getSize(), totalElements);

        List<ProductResponse> productResponses = filteredProducts.subList(fromIndex, toIndex)
                .stream()
                .map(ProductResponse::from)
                .toList();

        return ProductSearchResponse.builder()
                .items(productResponses)
                .page(request.getPage())
                .size(request.getSize())
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }

    private boolean matchesQuery(Product product, String query) {
        if (query == null || query.isBlank()) {
            return true;
        }

        String normalizedQuery = query.trim().toLowerCase(Locale.ROOT);

        return containsIgnoreCase(product.getTitle(), normalizedQuery)
                || containsIgnoreCase(product.getDescription(), normalizedQuery);
    }

    private boolean containsIgnoreCase(String value, String normalizedQuery) {
        if (value == null) {
            return false;
        }

        return value.toLowerCase(Locale.ROOT).contains(normalizedQuery);
    }

    private boolean matchesCategory(Product product, ProductCategory category) {
        if (category == null) {
            return true;
        }

        return product.getCategory() == category;
    }

    private boolean matchesMinPrice(Product product, BigDecimal minPrice) {
        if (minPrice == null) {
            return true;
        }

        return product.getPrice().compareTo(minPrice) >= 0;
    }

    private boolean matchesMaxPrice(Product product, BigDecimal maxPrice) {
        if (maxPrice == null) {
            return true;
        }

        return product.getPrice().compareTo(maxPrice) <= 0;
    }

    private Comparator<Product> getComparator(ProductSortOption sort) {
        ProductSortOption effectiveSort = sort == null ? ProductSortOption.RELEVANCE : sort;

        return switch (effectiveSort) {
            case PRICE_ASC -> Comparator.comparing(Product::getPrice);
            case PRICE_DESC -> Comparator.comparing(Product::getPrice).reversed();
            case RATING_DESC -> Comparator.comparingDouble(Product::getRating).reversed();
            case NEWEST -> Comparator.comparing(Product::getCreatedAt).reversed();
            case RELEVANCE -> Comparator.comparingDouble(Product::getRating).reversed();
        };
    }

    private int calculateTotalPages(int totalElements, int size) {
        if (totalElements == 0) {
            return 0;
        }

        return (int) Math.ceil((double) totalElements / size);
    }
}