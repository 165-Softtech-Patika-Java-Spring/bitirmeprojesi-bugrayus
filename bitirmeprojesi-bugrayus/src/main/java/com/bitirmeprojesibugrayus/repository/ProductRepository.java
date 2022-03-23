package com.bitirmeprojesibugrayus.repository;

import com.bitirmeprojesibugrayus.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByCategoryId(long categoryId);

    List<Product> findProductByPriceBetween(BigDecimal priceLow, BigDecimal priceHigh);
}
