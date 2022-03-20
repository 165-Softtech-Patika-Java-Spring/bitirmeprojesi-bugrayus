package com.bitirmeprojesibugrayus.repository;

import com.bitirmeprojesibugrayus.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
