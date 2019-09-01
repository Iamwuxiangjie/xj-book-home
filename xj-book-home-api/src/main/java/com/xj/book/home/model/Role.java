package com.xj.book.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="role")
@GenericGenerator(name = "role_uuid", strategy = "uuid")
public class Role {

    @Id
    @GeneratedValue(generator = "role_uuid")
    @Column(length = 32)
    @Getter
    @Setter
    private String id;

    @Column(unique=true,nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;


    @Getter
    @Setter
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime = new Date();
}
