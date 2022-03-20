package com.bitirmeprojesibugrayus.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCategoryRequestModel {
    String name;
    BigDecimal KDV;
}
