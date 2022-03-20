package com.bitirmeprojesibugrayus.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User extends BaseModel {
    String name;
    String surname;
    String username;
    String password;
}
