package com.bitirmeprojesibugrayus.repository;

import com.bitirmeprojesibugrayus.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByCategoryId(long categoryId);

    List<Product> findProductByPriceBetween(BigDecimal priceLow, BigDecimal priceHigh);

    boolean existsByCategoryId(long categoryId);
}
