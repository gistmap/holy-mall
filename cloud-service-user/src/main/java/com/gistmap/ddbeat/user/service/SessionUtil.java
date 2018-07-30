package com.gistmap.ddbeat.user.service;

import com.gistmap.common.json.JsonConverter;
import com.gistmap.ddbeat.user.persistence.domain.User;
import com.gistmap.ddbeat.user.service.dto.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhangran
 * @date 2018/7/10
 */
@Component
public class SessionUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String userSessionPrefix = "usr:session:";

    private String generateUserKeyBy(String token) {
        return userSessionPrefix + token;
    }

    public Session createSession(User user) {
        Session session = Session.newInstance(user);
        String json = JsonConverter.toJson(session);
        String key = generateUserKeyBy(session.token);
        stringRedisTemplate.opsForValue().set(key, json);
        return session;
    }
}
