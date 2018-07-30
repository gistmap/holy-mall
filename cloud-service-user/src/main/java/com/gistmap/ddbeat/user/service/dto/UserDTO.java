package com.gistmap.ddbeat.user.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gistmap.ddbeat.user.persistence.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author zhangran
 * @date 2018/7/9
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDTO {
    /**
     * save
     */
    @Email
    @NotBlank(message = "邮箱不能为空")
    public String email;
    @NotBlank(message = "姓名不能为空")
    public String name;
    public String password;

    public UserDTO(User user) {
        if (user == null) {
            BeanUtils.copyProperties(user, this);
        }
    }
}
