package com.xj.book.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Document(collection="user")
public class User {

    @Id
    @JsonIgnore
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String nickname;

    @Getter
    @Setter
    @Indexed(unique = true,background = true)
    private String phone;

    @JsonIgnore
    @Getter
    private String password;

    public void setPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    @Getter
    @Setter
    private Boolean active = true;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date lastLoginTime;
}
