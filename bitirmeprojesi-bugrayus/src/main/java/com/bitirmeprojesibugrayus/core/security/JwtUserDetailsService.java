package com.bitirmeprojesibugrayus.core.security;

import com.bitirmeprojesibugrayus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserDetails.create(userRepository.findByUsername(username));
    }

    public UserDetails loadUserByUserId(Long id) {
        return JwtUserDetails.create(userRepository.getUserById(id));
    }
}
