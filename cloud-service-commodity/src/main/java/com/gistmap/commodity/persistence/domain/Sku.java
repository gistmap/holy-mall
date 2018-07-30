package com.gistmap.commodity.persistence.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 规格
 * @author zhangran
 * @date 2018/7/26
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sku")
public class Sku {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    private Integer attrId;

    private String value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;
}
