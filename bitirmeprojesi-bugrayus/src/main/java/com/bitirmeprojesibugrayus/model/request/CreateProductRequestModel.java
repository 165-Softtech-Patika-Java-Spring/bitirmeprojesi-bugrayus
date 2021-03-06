package com.bitirmeprojesibugrayus.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class CreateProductRequestModel {
    @NotNull
    String name;
    @NotNull
    long categoryId;
    @NotNull
    BigDecimal priceWithoutTax;
}
