package com.bitirmeprojesibugrayus.model.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateCategoryRequestModel {
    String name;
    BigDecimal taxPercent;
}
