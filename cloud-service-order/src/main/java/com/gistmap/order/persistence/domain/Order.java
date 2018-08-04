package com.gistmap.order.persistence.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangran
 * @date 2018/8/2
 */
@Getter
@Setter
@Entity
@Table(name = "tb_order")
@Where(clause = "is_delete=0")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    private String id;

    private String uid;
    private String info;
    /**
     * 实付金额
     */
    private Integer amount;
    /**
     * 优惠金额
     */
    private Integer discount;
    private String paymentChannel;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date paymentTime;
    private Date completeTime;

    /**
     * 收货地址 ID
     */
    private String addressId;
    /**
     * 商户 ID
     */
    private String merchantId;


    @Column(name = "is_delete")
    boolean delete;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @OneToMany(
            mappedBy = "order",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<OrderDetail> details = new ArrayList<>();
}
