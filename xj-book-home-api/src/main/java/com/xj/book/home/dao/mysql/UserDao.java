package com.xj.book.home.dao.mysql;

import com.xj.book.home.model.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findFirstByPhone(String phone);

}
