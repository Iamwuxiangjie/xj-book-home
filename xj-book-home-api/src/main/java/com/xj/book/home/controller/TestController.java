package com.xj.book.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.xj.book.home.dao.RoleDao;
import com.xj.book.home.dao.UserDao;
import com.xj.book.home.dao.UserRoleDao;
import com.xj.book.home.model.Role;
import com.xj.book.home.model.User;
import com.xj.book.home.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @GetMapping("/{id}")
    public User test(@PathVariable("id") String id){
        return userDao.findById(id).get();
    }

    @GetMapping("/add/{name}")
    @Transactional
    public User add(@PathVariable("name") String name){
        User hasExistUser=userDao.findFirstByPhone(name);
        if(Objects.isNull(hasExistUser)){
            hasExistUser=new User();
            hasExistUser.setPhone(name);
            hasExistUser.setUsername(name);
            hasExistUser.setPassword("123");
            hasExistUser.setLastLoginTime(new Date());
            userDao.save(hasExistUser);
        }
        Role hasExistRole=roleDao.findFirstByName(name);
        if(Objects.isNull(hasExistRole)){
            hasExistRole=new Role();
            hasExistRole.setName("admin");
            hasExistRole.setDescription("管理员");
            roleDao.save(hasExistRole);
        }
        UserRole hasExistUserRole=userRoleDao.findFirstByUserIdAndRoleId(hasExistUser.getId(),hasExistRole.getId());
        if(Objects.isNull(hasExistUserRole)){
            hasExistUserRole=new UserRole();
            hasExistUserRole.setUserId(hasExistUser.getId());
            hasExistUserRole.setRoleId(hasExistRole.getId());
            userRoleDao.save(hasExistUserRole);
        }
        return hasExistUser;
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
