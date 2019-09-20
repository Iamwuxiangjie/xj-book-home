package com.xj.book.home.controller;

import com.xj.book.home.dao.mongo.LoginHistoryDao;
import com.xj.book.home.dao.mysql.RoleDao;
import com.xj.book.home.dao.mysql.UserDao;
import com.xj.book.home.dao.mysql.UserRoleDao;
import com.xj.book.home.model.mongo.LoginHistory;
import com.xj.book.home.model.mysql.Role;
import com.xj.book.home.model.mysql.User;
import com.xj.book.home.model.mysql.UserRole;
import com.xj.book.home.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private LoginHistoryDao loginHistoryDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


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
        }else{
            return hasExistUser;
        }
        Role hasExistRole=roleDao.findFirstByName("admin");
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
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setIp("127.0.0.1");
        loginHistory.setUserId(hasExistUser.getId());
        loginHistory.setUsername(hasExistUser.getUsername());
        loginHistoryDao.save(loginHistory);
        return hasExistUser;
    }

    @GetMapping("/redis/{key}")
    public String setAndGet(@PathVariable("key") String key, @RequestParam(value="value",required = false) String value){
        ValueOperations<String,String> valueOperations =stringRedisTemplate.opsForValue();
        if(!StringUtils.isEmpty(value)){
            valueOperations.set(key,value);
        }
        return MapperUtils.originalSuccess(valueOperations.get(key));
    }

}
