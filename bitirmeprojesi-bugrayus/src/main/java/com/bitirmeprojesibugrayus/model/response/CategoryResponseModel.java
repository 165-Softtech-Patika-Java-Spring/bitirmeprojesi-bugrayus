package com.bitirmeprojesibugrayus.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryResponseModel {
    Long id;
    String name;
    BigDecimal KDV;
}
