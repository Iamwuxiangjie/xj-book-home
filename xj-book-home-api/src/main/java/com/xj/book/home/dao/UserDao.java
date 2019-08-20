package com.xj.book.home.dao;

import com.xj.book.home.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {

    User findByPhone(String phone);
}
