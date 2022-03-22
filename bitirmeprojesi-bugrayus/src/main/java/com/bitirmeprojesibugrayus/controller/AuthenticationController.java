package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.core.model.ApiResponse;
import com.bitirmeprojesibugrayus.model.request.CreateUserRequestModel;
import com.bitirmeprojesibugrayus.model.request.LoginRequestModel;
import com.bitirmeprojesibugrayus.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Boolean>> register(@RequestBody CreateUserRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(authenticationService.register(requestModel)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(authenticationService.login(requestModel)));
    }
}
