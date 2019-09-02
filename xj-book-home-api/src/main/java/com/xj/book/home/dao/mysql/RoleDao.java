package com.xj.book.home.dao.mysql;

import com.xj.book.home.model.mysql.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {


    Role findFirstByName(String name);
}
