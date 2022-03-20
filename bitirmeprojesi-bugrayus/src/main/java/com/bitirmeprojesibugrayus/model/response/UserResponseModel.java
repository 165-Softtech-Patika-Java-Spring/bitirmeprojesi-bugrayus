package com.bitirmeprojesibugrayus.model.response;

import lombok.Data;

@Data
public class UserResponseModel {
    Long id;
    String name;
    String surname;
    String username;
    String password;
}
