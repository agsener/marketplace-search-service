package com.gokhansener.marketplacesearch.product.controller;

import com.gokhansener.marketplacesearch.product.dto.ProductSearchRequest;
import com.gokhansener.marketplacesearch.product.dto.ProductSearchResponse;
import com.gokhansener.marketplacesearch.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ProductSearchResponse searchProducts(@Valid @ModelAttribute ProductSearchRequest request) {
        return productService.searchProducts(request);
    }
}
