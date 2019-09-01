package com.xj.book.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="role_permission")
@GenericGenerator(name = "role_permission_uuid", strategy = "uuid")
public class RolePermission {

    @Id
    @GeneratedValue(generator = "role_permission_uuid")
    @Column(length = 32)
    @Getter
    @Setter
    private String id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String roleId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String permissionId;


    @Getter
    @Setter
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime = new Date();
}
