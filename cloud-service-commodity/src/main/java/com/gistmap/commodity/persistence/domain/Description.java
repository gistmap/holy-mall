package com.gistmap.commodity.persistence.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品详情
 * @author zhangran
 * @date 2018/7/26
 */
@Getter
@Setter
@Entity
@Table(name = "tb_description")
public class Description {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;
    // todo
}
