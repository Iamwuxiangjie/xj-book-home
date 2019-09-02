package com.xj.book.home.service.impl;

import com.xj.book.home.dao.mysql.RoleDao;
import com.xj.book.home.dao.mysql.UserRoleDao;
import com.xj.book.home.model.mysql.UserRole;
import com.xj.book.home.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseServiceImpl implements BaseService {


    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public List<String> listByUserId(String userId){
        List<UserRole> userRoles = userRoleDao.findByUserId(userId);
        List<String> roles = userRoles.stream().map(item -> String.format("ROLE_%s",roleDao.findById(item.getRoleId()).get().getName())).collect(Collectors.toList());
        return roles;
    }
}
