package com.gistmap.ddbeat.user.persistence.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhangran
 * @date 2018/7/9
 */
@Data
@Entity
@Table(name = "tb_user")
@Where(clause = "del_flag=0")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    private String name;


    private String password;
    private String email;

    private boolean delFlag;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

}
