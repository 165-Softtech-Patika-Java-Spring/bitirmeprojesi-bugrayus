package com.bitirmeprojesibugrayus.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateProductRequestModel {
    @NotNull
    String name;
    @NotNull
    long categoryId;
    @NotNull
    BigDecimal priceWithoutTax;
}
