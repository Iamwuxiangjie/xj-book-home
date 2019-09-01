package com.xj.book.home.dao;

import com.xj.book.home.model.Permission;
import com.xj.book.home.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole> {

    List<UserRole> findByUserId(String userId);

    UserRole findFirstByUserIdAndRoleId(String userId,String roleId);
}
