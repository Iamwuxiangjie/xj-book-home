package com.xj.book.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="role_permission")
public class RolePermission extends BaseEntity{


    @Column(nullable = false)
    @Getter
    @Setter
    private String roleId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String permissionId;

}
