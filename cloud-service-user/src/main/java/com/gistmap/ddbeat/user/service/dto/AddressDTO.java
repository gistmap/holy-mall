package com.gistmap.ddbeat.user.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.NoArgsConstructor;

/**
 * @author zhangran
 * @date 2018/8/2
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddressDTO {

    public String id;

    public String name;

    public String info;

    public String mobile;

    public String uid;
}
