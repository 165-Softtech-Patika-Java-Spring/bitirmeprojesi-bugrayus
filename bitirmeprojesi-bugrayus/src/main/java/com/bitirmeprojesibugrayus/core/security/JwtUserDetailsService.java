package com.bitirmeprojesibugrayus.core.security;

import com.bitirmeprojesibugrayus.model.User;
import com.bitirmeprojesibugrayus.service.CusCustomerEntityService;
import com.bitirmeprojesibugrayus.service.UserService;
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

        User cusCustomer = cusCustomerEntityService.findByIdentityNo(username);

        return JwtUserDetails.create(cusCustomer);
    }

    public UserDetails loadUserByUserId(Long id) {

        User cusCustomer = cusCustomerEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(cusCustomer);
    }
}
