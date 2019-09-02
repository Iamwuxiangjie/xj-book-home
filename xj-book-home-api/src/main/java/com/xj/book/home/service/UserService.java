package com.xj.book.home.service;

import com.xj.book.home.model.mysql.User;

public interface UserService {

    User login(String username, String password);
}
