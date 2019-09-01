package com.xj.book.home.controller;

import com.xj.book.home.dao.RoleDao;
import com.xj.book.home.dao.UserDao;
import com.xj.book.home.dao.UserRoleDao;
import com.xj.book.home.model.Role;
import com.xj.book.home.model.User;
import com.xj.book.home.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

}
