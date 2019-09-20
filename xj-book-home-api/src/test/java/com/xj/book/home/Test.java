package com.xj.book.home;

import com.xj.book.home.model.mysql.User;

import java.util.List;
import java.util.Optional;

public class Test {

    public static void main(String args[]) {
        User user = getUser(1l);
        System.out.println(Optional.ofNullable(user).map(i->i.getPhone()).get());
    }

    private static User getUser(Long num) {
        User user = new User();
        user.setUsername(num.toString());
        user.setPhone(num.toString());
        return user;
    }
}
