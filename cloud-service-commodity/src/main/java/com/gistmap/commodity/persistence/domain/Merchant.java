package com.gistmap.commodity.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gistmap.commodity.persistence.enumeration.ReviewStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商户
 * @author zhangran
 * @date 2018/7/26
 */
@Getter
@Setter
@Entity
@Table(name = "tb_merchant")
@EntityListeners(AuditingEntityListener.class)
public class Merchant {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    private String name;
    private String address;
    private String contact;
    private String mobile;
    private String image;
    /**
     * 是否开店
     */
    private boolean active;
    /**
     * 审核状态
     */
    private ReviewStatus status;

    /**
     * 一个商户只局限于一个分类
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(
            mappedBy = "merchant",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Goods> goods = new ArrayList<>();

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "is_delete")
    boolean delete;

    public void addGoods(Goods good) {
        goods.add(good);
        good.setMerchant(this);
    }

    public void removeGoods(Goods good) {
        goods.remove(good);
        good.setMerchant(null);
    }

}
