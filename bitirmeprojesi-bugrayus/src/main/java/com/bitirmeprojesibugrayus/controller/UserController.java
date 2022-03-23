package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.model.request.UpdateUserRequestModel;
import com.bitirmeprojesibugrayus.model.response.UserResponseModel;
import com.bitirmeprojesibugrayus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public UserResponseModel findUserById(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<UserResponseModel> findUsers() {
        return userService.getUsers();
    }


    @PutMapping("/user")
    public boolean updateUser(@RequestBody UpdateUserRequestModel requestModel) {
        return userService.updateUser(requestModel);
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
