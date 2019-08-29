package com.xj.book.home.controller;


import com.xj.book.home.model.User;
import com.xj.book.home.utils.MapperUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class BaseController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user){
        if(Objects.isNull(user)){
            return MapperUtils.originalForward("login");
        }else{
            return MapperUtils.originalForward("index");
        }
    }

    @GetMapping("/web/admin/testAdmin")
    public String testAdmin(){
        return MapperUtils.originalSuccess("success");
    }

    @GetMapping("/testUser")
    public String testUser(@AuthenticationPrincipal User user){
        return MapperUtils.originalSuccess(user);
    }

}
