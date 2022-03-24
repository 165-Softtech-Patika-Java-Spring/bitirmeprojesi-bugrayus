package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.core.model.ApiResponse;
import com.bitirmeprojesibugrayus.model.request.UpdateUserRequestModel;
import com.bitirmeprojesibugrayus.model.response.UserResponseModel;
import com.bitirmeprojesibugrayus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/id/{id}")
    public ResponseEntity<ApiResponse<UserResponseModel>> findUserById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.of(
                userService.findUserById(id)
        ));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponseModel>>> findUsers() {
        return ResponseEntity.ok(ApiResponse.of(
                userService.findUsers()
        ));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse<Boolean>> updateUser(@RequestBody UpdateUserRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                userService.updateUser(requestModel)
        ));
    }

    @DeleteMapping("/user/id/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(
                userService.deleteUser(id)
        ));
    }
}
