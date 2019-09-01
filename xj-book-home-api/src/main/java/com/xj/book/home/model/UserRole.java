package com.xj.book.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_role")
public class UserRole extends BaseEntity {

    @Column(nullable = false)
    @Getter
    @Setter
    private String userId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String roleId;
}