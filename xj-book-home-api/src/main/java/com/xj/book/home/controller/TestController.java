package com.xj.book.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.xj.book.home.dao.UserDao;
import com.xj.book.home.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/{id}")
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

    @GetMapping("/get")
    public JSONObject get(@RequestParam("name") String name){
        return ok(name);
    }

    @PostMapping("/post")
    public JSONObject post(@RequestParam("name") String name){
        return ok(name);
    }

    @GetMapping("/error")
    public JSONObject error(@RequestParam("name") String name){
        Assert.isTrue(name==null,"我错了");
        return ok(name);
    }


    private JSONObject ok(String message){
        Map<String,Object> result= new HashMap(){{
           put("code",1);
           put("data",message);
        }};
        return new JSONObject(result);
    }
}
