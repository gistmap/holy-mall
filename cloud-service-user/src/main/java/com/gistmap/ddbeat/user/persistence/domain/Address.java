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
 * @date 2018/8/2
 */
@Data
@Entity
@Table(name = "tb_address")
@Where(clause = "del_flag=0")
@EntityListeners(AuditingEntityListener.class)
public class Address {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    private String name;

    private String info;

    private String mobile;
    /**
     * 是否默认地址
     */
    private boolean acquiescence;

    private boolean delFlag;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    /**
     * 级联关系
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
