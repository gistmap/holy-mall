package com.gistmap.commodity.service.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangran
 * @date 2018/7/31
 */
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGoodsVO {
    public String categoryName;
    public List<GoodsVO> goods;
}
