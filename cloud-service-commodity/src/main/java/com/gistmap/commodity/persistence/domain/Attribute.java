package com.gistmap.commodity.persistence.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * sku属性
 * @author zhangran
 * @date 2018/7/26
 */
@Getter
@Setter
@Entity
@Table(name = "tb_attribute")
@EntityListeners(AuditingEntityListener.class)
public class Attribute {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
}
