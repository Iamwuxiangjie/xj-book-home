package com.xj.book.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="permission")
public class Permission extends BaseEntity {

    @Column(unique=true,nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;
;
}
