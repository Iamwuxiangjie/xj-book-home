package com.xj.book.home.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="persistent_logins")
public class PersistentLogins {


    @Column(length = 64,unique = true,nullable = false)
    @Getter
    @Setter
    @Id
    private String series;

    @Column(length = 64,nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(length = 64,nullable = false)
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date last_used;

}
