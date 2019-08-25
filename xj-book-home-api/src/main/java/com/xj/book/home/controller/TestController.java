package com.xj.book.home.controller;

import com.xj.book.home.dao.UserRep;
import com.xj.book.home.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserRep userRep;

    @GetMapping("/test/{id}")
    public User test(@PathVariable("id") Long id){
        return userRep.findById(id).get();
    }
}
