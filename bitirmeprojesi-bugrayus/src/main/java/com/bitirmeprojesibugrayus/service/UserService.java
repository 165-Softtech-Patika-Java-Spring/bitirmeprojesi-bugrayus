package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.core.security.JwtTokenGenerator;
import com.bitirmeprojesibugrayus.model.User;
import com.bitirmeprojesibugrayus.model.request.CreateUserRequestModel;
import com.bitirmeprojesibugrayus.model.request.LoginRequestModel;
import com.bitirmeprojesibugrayus.model.response.UserResponseModel;
import com.bitirmeprojesibugrayus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public boolean register(CreateUserRequestModel requestModel) {
        if (userRepository.existsByUsername(requestModel.getUsername()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists.");

//        mapper.getConfiguration().setAmbiguityIgnored(true);
        requestModel.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        userRepository.save(mapper.map(requestModel, User.class));
        return true;
    }

    public String login(LoginRequestModel requestModel) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateJwtToken(authentication);
        return "Bearer " + token;
    }

    public UserResponseModel getUser(long id) {
        if (!userRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist.");
        return mapper.map(userRepository.getById(id), UserResponseModel.class);
    }
//
//    public UserResponseModel getUser(String username) {
//        if (!userRepository.existsByUsername(username))
//            return null;
//        return mapper.map(userRepository.findByUsername(username), UserResponseModel.class);
//    }
//
//    public List<UserResponseModel> getUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream().map(x -> mapper.map(x, UserResponseModel.class)).collect(Collectors.toList());
//    }
//
//
//    public boolean deleteUser(DeleteUserRequestModel requestModel) {
//        if (!userRepository.existsByUsernameAndPhone(requestModel.getUsername(), requestModel.getPhone()))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, requestModel.getUsername() + " Does not match with " + requestModel.getPhone() + " phone number");
//        userRepository.delete(userRepository.findByUsernameAndPhone(requestModel.getUsername(), requestModel.getPhone()));
//        return true;
//    }
//
//    public boolean updateUser(UpdateUserRequestModel requestModel) {
//        if (!userRepository.existsById(requestModel.getId()))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by that id");
//        User user = userRepository.getById(requestModel.getId());
//        mapper.map(requestModel, user);
//        userRepository.save(user);
//        return true;
//    }
}
