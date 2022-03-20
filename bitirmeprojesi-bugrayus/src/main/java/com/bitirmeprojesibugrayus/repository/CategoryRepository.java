package com.bitirmeprojesibugrayus.repository;

import com.bitirmeprojesibugrayus.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
