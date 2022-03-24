package com.bitirmeprojesibugrayus.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryResponseModel {
    Long id;
    String name;
    BigDecimal taxPercent;
}
