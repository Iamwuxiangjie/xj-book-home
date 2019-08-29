package com.xj.book.home.dao;

import com.xj.book.home.model.Role;
import com.xj.book.home.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {


    Role findFirstByName(String name);
}
