package com.xj.book.home.model.mysql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole extends MysqlBaseEntity {

    @Column(nullable = false)
    @Getter
    @Setter
    private String userId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String roleId;
}