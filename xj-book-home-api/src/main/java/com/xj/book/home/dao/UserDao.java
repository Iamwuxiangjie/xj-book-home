package com.xj.book.home.dao;

import com.xj.book.home.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByPhone(String phone);
}
