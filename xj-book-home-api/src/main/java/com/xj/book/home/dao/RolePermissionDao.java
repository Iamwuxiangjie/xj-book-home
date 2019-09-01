package com.xj.book.home.dao;

import com.xj.book.home.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionDao extends JpaRepository<RolePermission, String>, JpaSpecificationExecutor<RolePermission> {

    List<RolePermission> findByRoleId(String roleId);
}
