package com.xj.book.home.service.impl;

import com.xj.book.home.dao.mysql.UserDao;
import com.xj.book.home.model.mysql.User;
import com.xj.book.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        if(StringUtils.isEmpty(username)){
            return null;
        }
        if(StringUtils.isEmpty(password)){
            return null;
        }
        User user=userDao.findFirstByPhone(username);
        if(Objects.isNull(user)){
            return null;
        }else if(user.checkPassword(password)){
            return user;
        }else{
            return null;
        }
    }

}
