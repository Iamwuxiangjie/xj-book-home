package com.xj.book.home.model.mysql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role extends MysqlBaseEntity {

    @Column(unique=true,nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;
}
