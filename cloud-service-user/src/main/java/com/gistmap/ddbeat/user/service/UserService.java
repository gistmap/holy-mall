package com.gistmap.ddbeat.user.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gistmap.common.exception.BaseException;
import com.gistmap.common.json.JsonConverter;
import com.gistmap.common.util.Const;
import com.gistmap.common.util.MD5Util;
import com.gistmap.ddbeat.user.persistence.domain.User;
import com.gistmap.ddbeat.user.persistence.repository.UserRepository;
import com.gistmap.ddbeat.user.service.dto.Session;
import com.gistmap.ddbeat.user.service.dto.UserDTO;
import com.gistmap.ddbeat.user.service.remote.MailService;
import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author zhangran
 * @date 2018/7/9
 */
@Slf4j
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private SessionUtil sessionUtil;

    @Transactional(rollbackFor = BaseException.class)
    public void signup(String name, String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new BaseException("此用户已存在!");
        }
        user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(getPwd(password));
        userRepository.save(user);

        // mq 发邮件
        ObjectNode nodes = JsonConverter.newJson();
        nodes.put("to", email);
        nodes.put("name", name);

        mailService.sendMail(JsonConverter.toJson(nodes));
    }

    @Transactional(rollbackFor = BaseException.class)
    public Session signin(String mobile, String password) {
        User user = userRepository.findByEmailOrName(mobile);
        if (user != null) {
            if (user.isDelFlag()){
                throw new BaseException("用户已禁用");
            }
            if (getPwd(password).equals(user.getPassword())) {
                return sessionUtil.createSession(user);
            } else {
                throw new BaseException("密码错误");
            }
        } else {
            throw new BaseException("用户不存在");
        }
    }

    protected String getPwd(String password){
        try {
            String pwd = MD5Util.encrypt(password+Const.PASSWORD_KEY);
            return pwd;
        } catch (Exception e) {
            log.error("密码加密异常：",e);
        }
        return null;
    }


    private User get(String uid){
        Optional<User> user = userRepository.findById(uid);
         return user.orElseThrow(() -> new BaseException("不存在的用户"));
    }

    public Page<UserDTO> list(String mobile, Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    public Page<User> findAll(String mobile, Pageable pageable){
        Specification<User> specification = Specifications.<User>and()
                .eq(StringUtils.isNotBlank(mobile),"mobile", mobile).build();
        Sort sort = Sorts.builder()
                .asc("create_time")
                .build();
        return userRepository.findAll(specification,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));

    }

    public UserDTO find(String id) {
        return new UserDTO(get(id));
    }
}
