package com.gokhansener.marketplacesearch.product.repository;

import com.gokhansener.marketplacesearch.product.model.Product;
import com.gokhansener.marketplacesearch.product.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products = List.of(
            new Product(
                    1L,
                    "iPhone 15",
                    "Apple iPhone 15 128GB",
                    ProductCategory.ELECTRONICS,
                    BigDecimal.valueOf(899.99),
                    12,
                    4.7,
                    LocalDateTime.now().minusDays(3)
            ),
            new Product(
                    2L,
                    "Samsung Galaxy S24",
                    "Samsung Galaxy S24 256GB",
                    ProductCategory.ELECTRONICS,
                    BigDecimal.valueOf(799.99),
                    8,
                    4.6,
                    LocalDateTime.now().minusDays(5)
            ),
            new Product(
                    3L,
                    "MacBook Pro 14",
                    "Apple MacBook Pro 14 inch M3",
                    ProductCategory.ELECTRONICS,
                    BigDecimal.valueOf(1999.99),
                    4,
                    4.9,
                    LocalDateTime.now().minusDays(10)
            ),
            new Product(
                    4L,
                    "Wooden Dining Table",
                    "Solid wood dining table for six people",
                    ProductCategory.HOME,
                    BigDecimal.valueOf(450.00),
                    2,
                    4.3,
                    LocalDateTime.now().minusDays(1)
            ),
            new Product(
                    5L,
                    "Road Bike",
                    "Lightweight road bike with Shimano gears",
                    ProductCategory.VEHICLES,
                    BigDecimal.valueOf(650.00),
                    3,
                    4.4,
                    LocalDateTime.now().minusDays(7)
            ),
            new Product(
                    6L,
                    "Running Shoes",
                    "Comfortable running shoes for daily training",
                    ProductCategory.SPORTS,
                    BigDecimal.valueOf(120.00),
                    20,
                    4.2,
                    LocalDateTime.now().minusDays(2)
            ),
            new Product(
                    7L,
                    "Clean Code",
                    "A handbook of agile software craftsmanship",
                    ProductCategory.BOOKS,
                    BigDecimal.valueOf(35.00),
                    15,
                    4.8,
                    LocalDateTime.now().minusDays(20)
            ),
            new Product(
                    8L,
                    "Leather Jacket",
                    "Black leather jacket",
                    ProductCategory.FASHION,
                    BigDecimal.valueOf(180.00),
                    6,
                    4.1,
                    LocalDateTime.now().minusDays(4)
            )
    );

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }
}
