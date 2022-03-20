package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.core.model.ApiResponse;
import com.bitirmeprojesibugrayus.model.request.CreateUserRequestModel;
import com.bitirmeprojesibugrayus.model.request.LoginRequestModel;
import com.bitirmeprojesibugrayus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<Boolean>> register(@RequestBody CreateUserRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(userService.register(requestModel)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(userService.login(requestModel)));
    }

    //    @GetMapping("/user/{id}")
//    public UserResponseModel getUser(@PathVariable int id) {
//        return userService.getUser(id);
//    }
//
//    @GetMapping("/user/{username}/username")
//    public UserResponseModel getUser(@PathVariable String username) {
//        return userService.getUser(username);
//    }
//
//    @GetMapping("/users")
//    public List<UserResponseModel> getUsers() {
//        return userService.getUsers();
//    }
//
//
//    @PutMapping("/user")
//    public boolean updateUser(@RequestBody UpdateUserRequestModel requestModel) {
//        return userService.updateUser(requestModel);
//    }
//
//    @DeleteMapping("/user")
//    public boolean deleteUser(@RequestBody DeleteUserRequestModel requestModel) {
//        return userService.deleteUser(requestModel);
//    }
}
