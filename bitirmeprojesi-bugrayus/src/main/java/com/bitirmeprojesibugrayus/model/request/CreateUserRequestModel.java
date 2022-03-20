package com.bitirmeprojesibugrayus.model.request;

import lombok.Data;

@Data
public class CreateUserRequestModel {
    String name;
    String surname;
    String username;
    String password;
}
