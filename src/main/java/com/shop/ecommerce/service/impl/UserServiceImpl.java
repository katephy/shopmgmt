package com.shop.ecommerce.service.impl;

import com.shop.ecommerce.dao.UserDao;
import com.shop.ecommerce.entity.User;
import com.shop.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(Long userId) {
        return userDao.queryUserById(userId);
    }
}
