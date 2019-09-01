package com.xj.book.home.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="user")
public class User extends BaseEntity {

    @Column(length = 32,unique=true,nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(length = 11,unique=true,nullable = false)
    @Getter
    @Setter
    private String phone;

    @Column(length = 64,nullable = false)
    @Getter
    @JsonIgnore
    private String password;

    public void setPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public Boolean checkPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, this.getPassword());
    }

    @Getter
    @Setter
    @Column(columnDefinition = "int(1) not null default 1")
    private Boolean active = true;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date lastLoginTime;

    @Override
    public String toString() {
        return this.username;
    }
}
