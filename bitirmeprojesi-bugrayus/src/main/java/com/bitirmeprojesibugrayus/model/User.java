package com.bitirmeprojesibugrayus.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
@ToString
@RequiredArgsConstructor
public class User extends BaseModel {
    String name;
    String surname;
    String username;
    String password;
}
