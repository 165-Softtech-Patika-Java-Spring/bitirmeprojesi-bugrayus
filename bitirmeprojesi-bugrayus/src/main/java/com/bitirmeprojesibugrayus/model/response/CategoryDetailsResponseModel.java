package com.bitirmeprojesibugrayus.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryDetailsResponseModel {
    String categoryName;
    BigDecimal tax;
    BigDecimal minimumPrice;
    BigDecimal maximumPrice;
    BigDecimal averagePrice;
    int productCount;
}
