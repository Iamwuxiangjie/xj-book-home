package com.xj.book.home.controller;

import com.xj.book.home.dao.UserDao;
import com.xj.book.home.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

@RestController
public class TestController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/test/{id}")
    public User test(@PathVariable("id") String id){
        return userDao.findById(id).get();
    }

    @GetMapping("/add/{name}")
    public User add(@PathVariable("name") String name){
        User hasExist=userDao.findByPhone(name);
        if(Objects.isNull(hasExist)){
            User u=new User();
            u.setPhone(name);
            u.setPassword("123");
            u.setLastLoginTime(new Date());
            return userDao.save(u);
        }
        return hasExist;
    }

    @GetMapping("/")
    public User get(){
        return userDao.findByPhone("wuxj");
    }
}
