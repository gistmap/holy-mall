package com.gistmap.commodity.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @author zhangran
 * @date 2018/7/28
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MerchantDTO {
    public String name;
    public String address;
    public String contact;
    public String mobile;
    public String image;
    public Integer categoryId;
}
