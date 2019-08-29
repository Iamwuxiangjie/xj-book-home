package com.xj.book.home.controller;


import com.xj.book.home.dao.RoleDao;
import com.xj.book.home.model.User;
import com.xj.book.home.service.BaseService;
import com.xj.book.home.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class BaseController {

    @Autowired
    private BaseService baseService;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user){
        if(Objects.isNull(user)){
            return MapperUtils.originalForward("login");
        }else{
            return MapperUtils.originalForward("index");
        }
    }

    @GetMapping("/self")
    public String self(@AuthenticationPrincipal User user){
        if(Objects.isNull(user)){
            return MapperUtils.originalForward("login");
        }else{
            List<String> roles=baseService.listByUserId(user.getId());
            return MapperUtils.originalSuccess("user",user,"roles",roles);
        }
    }

}
