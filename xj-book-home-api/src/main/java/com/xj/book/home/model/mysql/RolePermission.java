package com.xj.book.home.model.mysql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="role_permission")
public class RolePermission extends MysqlBaseEntity {


    @Column(nullable = false)
    @Getter
    @Setter
    private String roleId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String permissionId;

}
