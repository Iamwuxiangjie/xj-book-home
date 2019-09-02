package com.xj.book.home.model.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author: WuXJ
 * @date: 2019-09-01 20:53
 * Copyright (c) 2019, ewell.com All Rights Reserved.
 */
@MappedSuperclass
public class MongoBaseEntity implements Serializable {

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
}
