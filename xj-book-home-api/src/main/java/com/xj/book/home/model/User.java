package com.xj.book.home.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="user")
@GenericGenerator(name = "user_uuid", strategy = "uuid")
public class User {

    @Id
    @GeneratedValue(generator = "user_uuid")
    @Column(length = 32)
    @Getter
    @Setter
    private String uid;

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
    private Boolean active = true;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date lastLoginTime;
}
