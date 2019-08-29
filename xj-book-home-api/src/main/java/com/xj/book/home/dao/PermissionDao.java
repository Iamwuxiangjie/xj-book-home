package com.xj.book.home.dao;

import com.xj.book.home.model.Permission;
import com.xj.book.home.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {


}
