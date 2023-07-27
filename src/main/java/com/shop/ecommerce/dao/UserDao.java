package com.shop.ecommerce.dao;

import com.shop.ecommerce.entity.User;

public interface UserDao {

    User queryUserById(long userId);
}
