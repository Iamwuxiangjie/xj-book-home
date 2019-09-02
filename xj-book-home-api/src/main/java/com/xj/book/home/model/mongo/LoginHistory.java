package com.xj.book.home.model.mongo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "login_history")
public class LoginHistory  implements Serializable {

    @Id
    @JsonIgnore
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime = new Date();

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime = new Date();

    @Getter
    @Setter
    @JsonIgnore
    private String userId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String ip;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date loginTime = new Date();
}
