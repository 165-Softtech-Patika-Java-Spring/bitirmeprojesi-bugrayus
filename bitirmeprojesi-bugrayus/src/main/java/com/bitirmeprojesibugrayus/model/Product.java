package com.bitirmeprojesibugrayus.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product extends BaseModel {
    String name;
    @ManyToOne
    Category category;
    BigDecimal price;
    BigDecimal tax;
}
