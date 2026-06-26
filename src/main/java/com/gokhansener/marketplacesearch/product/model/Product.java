package com.gokhansener.marketplacesearch.product.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

    private Long id;
    private String title;
    private String description;
    private ProductCategory category;
    private BigDecimal price;
    private int stock;
    private double rating;
    private LocalDateTime createdAt;

    public Product(
            Long id,
            String title,
            String description,
            ProductCategory category,
            BigDecimal price,
            int stock,
            double rating,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getRating() {
        return rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
