package com.xj.book.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_role")
@GenericGenerator(name = "user_role_uuid", strategy = "uuid")
public class UserRole {

    @Id
    @GeneratedValue(generator = "user_role_uuid")
    @Column(length = 32)
    @Getter
    @Setter
    private String id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String userId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String roleId;


    @Getter
    @Setter
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime = new Date();
}