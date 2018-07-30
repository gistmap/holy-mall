package com.gistmap.commodity.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gistmap.commodity.persistence.domain.Goods;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @author zhangran
 * @date 2018/7/27
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GoodsDTO {

    @NotBlank(message = "商品名不能为空")
    public String name;

    public String description;

    public String icon;

    public String image;

    @Min(value = 0, message = "商品价格不能小于0")
    public Integer price;
    @Min(value = 0, message = "商品折扣不能小于0")
    public Integer discount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date expirationTime;

    @Min(value = 0, message = "商品库存不能小于0")
    public Integer stock;

    @NotBlank(message = "商品名不能为空")
    public String merchantId;

    public List<SkuDTO> skus;

    public GoodsDTO(Goods goods) {
        if (goods != null) {
            BeanUtils.copyProperties(goods, this);
            this.merchantId = goods.getMerchant().getId();
        }
    }
}
