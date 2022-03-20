package com.bitirmeprojesibugrayus.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequestModel {
    String name;
    int categoryId;
    BigDecimal priceWithoutTax;
}
