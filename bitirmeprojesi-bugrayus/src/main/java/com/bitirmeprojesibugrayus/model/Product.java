package com.bitirmeprojesibugrayus.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product extends BaseModel {
    @NotNull
    String name;
    @ManyToOne
    @NotNull
    Category category;
    @NotNull
    BigDecimal price;
    BigDecimal tax;
}
