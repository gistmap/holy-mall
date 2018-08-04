package com.gistmap.commodity.persistence.domain;

import com.gistmap.commodity.persistence.enumeration.GoodsStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品表
 * @author zhangran
 * @date 2018/7/26
 */
@Getter
@Setter
@Entity
@Table(name = "tb_goods")
@Where(clause = "is_delete=0")
@EntityListeners(AuditingEntityListener.class)
public class Goods {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    private String name;

    private String description;

    private String icon;

    private String image;

    private Integer price;

    private Integer discount;

    private Date startTime;

    private Date expirationTime;

    /**
     * 库存
     */
    private Integer stock;
    /**
     * 总量 - 计算已售
     */
    private Integer total;
    /**
     * 上下架
     */
    @Enumerated(EnumType.STRING)
    private GoodsStatus status = GoodsStatus.UP;

    @OneToMany(
            mappedBy = "goods",
            orphanRemoval = true,
//            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Sku> skus = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @Column(name = "is_delete")
    boolean delete;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public void addSku(Sku sku){
        skus.add(sku);
        sku.setGoods(this);
    }

    public void removeSku(Sku sku){
        skus.remove(sku);
        sku.setGoods(null);
    }
}
