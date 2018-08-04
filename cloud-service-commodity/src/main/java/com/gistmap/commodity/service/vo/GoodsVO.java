package com.gistmap.commodity.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gistmap.commodity.persistence.domain.Goods;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangran
 * @date 2018/7/30
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GoodsVO {

    private String id;

    private String name;

    private String description;

    private String icon;

    private String image;

    private Integer price;

    private Integer discount;

    private Integer stock;

    private Integer sold;

//    private Merchant merchant;

    public GoodsVO(Goods goods){
        if (goods != null) {
            BeanUtils.copyProperties(goods, this);
            sold = goods.getTotal() - goods.getStock();
        }
    }
}
