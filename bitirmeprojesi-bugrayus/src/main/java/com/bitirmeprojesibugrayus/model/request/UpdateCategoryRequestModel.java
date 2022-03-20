package com.bitirmeprojesibugrayus.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateCategoryRequestModel {
    Long id;
    String name;
    BigDecimal KDV;
}
