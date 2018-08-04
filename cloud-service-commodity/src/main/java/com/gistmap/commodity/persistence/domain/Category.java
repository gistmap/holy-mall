package com.gistmap.commodity.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangran
 * @date 2018/7/27
 */
@Setter
@Getter
@Entity
@Table(name = "tb_category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer pid;

    @OneToMany(
            mappedBy="category",
            fetch = FetchType.LAZY,
            cascade={CascadeType.ALL}
    )
    @JsonIgnore
    private List<Merchant> merchants = new ArrayList<>();

    @OneToMany(
            mappedBy="category",
            fetch = FetchType.LAZY,
            cascade={CascadeType.ALL}
    )
    @JsonIgnore
    private List<Attribute> attributes = new ArrayList<>();
}
