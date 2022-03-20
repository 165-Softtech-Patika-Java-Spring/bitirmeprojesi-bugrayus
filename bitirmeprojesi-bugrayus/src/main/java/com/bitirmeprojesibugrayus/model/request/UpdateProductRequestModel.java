package com.bitirmeprojesibugrayus.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequestModel {
    Long id;
    BigDecimal price;
}
