package com.bitirmeprojesibugrayus.repository;

import com.bitirmeprojesibugrayus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
    User getUserById(long id);
//
//    User findByUsernameAndPhone(String username, String phone);
//
//
//    boolean existsByUsernameAndPhone(String username, String phone);
//
//    boolean existsByUsernameOrPhone(String username, String phone);
}
