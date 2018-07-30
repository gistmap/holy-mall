package com.gistmap.ddbeat.user.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gistmap.common.util.SerialGenerator;
import com.gistmap.ddbeat.user.persistence.domain.User;

/**
 * @author zhangran
 * @date 2018/7/9
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Session {
    public String token;
    public String id;
    public String name;
    public String secret;

    public static Session newInstance(User user) {
        Session session = new Session();
        session.token = SerialGenerator.randomUUID();
        session.name = user.getName();
        session.id = user.getId();
        session.secret = SerialGenerator.randomUUID();
        return session;
    }
}
