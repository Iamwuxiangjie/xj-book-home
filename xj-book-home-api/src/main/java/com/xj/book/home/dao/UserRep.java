package com.xj.book.home.dao;

import com.xj.book.home.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author: WuXJ
 * @date: 2019-08-18 18:38
 * Copyright (c) 2019, ewell.com All Rights Reserved.
 */
@Repository
public interface UserRep extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

}
