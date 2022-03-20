package com.bitirmeprojesibugrayus.model.response;

import com.bitirmeprojesibugrayus.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseModel {
    Long id;
    String name;
    Category category;
    BigDecimal price;
    BigDecimal tax;
}
