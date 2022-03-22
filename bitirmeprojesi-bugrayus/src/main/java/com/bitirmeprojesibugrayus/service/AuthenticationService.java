package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.core.security.JwtTokenGenerator;
import com.bitirmeprojesibugrayus.core.security.JwtUserDetails;
import com.bitirmeprojesibugrayus.model.User;
import com.bitirmeprojesibugrayus.model.request.CreateUserRequestModel;
import com.bitirmeprojesibugrayus.model.request.LoginRequestModel;
import com.bitirmeprojesibugrayus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final CusCustomerEntityService cusCustomerEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public boolean register(CreateUserRequestModel requestModel) {
        userService.createUser(requestModel);
        return true;
    }

    public String login(LoginRequestModel requestModel) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateJwtToken(authentication);
        return "Bearer " + token;
    }

    public Optional<User> findById(Long id) {
        return cusCustomerEntityService.findById(id);
    }

    public User getByIdWithControl(Long id) {
        Optional<User> entityOptional = findById(id);
        User entity;
        if (entityOptional.isPresent()) {
            entity = entityOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return entity;
    }

    public User findByIdentityNo(Long identityNo) {
        return cusCustomerEntityService.getByIdWithControl(identityNo);
    }

    public User getCurrentUser() {
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
        User user = null;
        if (jwtUserDetails != null) {
            user = cusCustomerEntityService.getByIdWithControl(jwtUserDetails.getId());
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        return user;
    }

    public Long getCurrentUserId() {
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null) {
            jwtUserDetailsId = jwtUserDetails.getId();
        }
        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails) {
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
