package com.bitirmeprojesibugrayus.core.security;

import com.bitirmeprojesibugrayus.model.User;
import com.bitirmeprojesibugrayus.repository.UserRepository;
import com.bitirmeprojesibugrayus.service.CusCustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CusCustomerEntityService cusCustomerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long identityNo = Long.valueOf(username);

        User user = cusCustomerEntityService.findByIdentityNo(identityNo);

        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserByUserId(Long id) {
        User user = cusCustomerEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(user);
    }
}
