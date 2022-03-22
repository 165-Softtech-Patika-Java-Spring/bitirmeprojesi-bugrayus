package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.model.User;
import com.bitirmeprojesibugrayus.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@Transactional
public class CusCustomerEntityService extends BaseEntityService<User, UserRepository> {

    public CusCustomerEntityService(UserRepository dao) {
        super(dao);
    }

    public List<User> findAllBySurname(String surname) {
        return getDao().findAll();
    }

    public User findByIdentityNo(Long identityNo) {
        return getDao().getUserById(identityNo);
    }
}
