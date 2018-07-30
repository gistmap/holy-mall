package com.gistmap.ddbeat.user.web;

import com.gistmap.common.exception.BaseException;
import com.gistmap.common.json.JsonResponseEntity;
import com.gistmap.ddbeat.user.service.UserService;
import com.gistmap.ddbeat.user.service.dto.Session;
import com.gistmap.ddbeat.user.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhangran
 * @date 2018/7/9
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/signin")
    public JsonResponseEntity<Session> signin(String username,
                                              String password) {
        JsonResponseEntity<Session> response = new JsonResponseEntity<>();
        response.data = userService.signin(username, password);
        return response;
    }

    @PostMapping
    public JsonResponseEntity<String> signup(@Valid @RequestBody UserDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new BaseException(bindingResult.getFieldError().getDefaultMessage());
        }
        userService.signup(body.name, body.email, body.password);
        JsonResponseEntity<String> response = new JsonResponseEntity<>();
        response.msg = "注册成功";
        return response;
    }


    @GetMapping("/list")
    public Page<UserDTO> list(String mobile, Pageable pageable){
        return userService.list(mobile, pageable);
    }

    @GetMapping
    public JsonResponseEntity<UserDTO> find(String id) {
        JsonResponseEntity<UserDTO> response = new JsonResponseEntity<>();
        response.data = userService.find(id);
        return response;
    }

}
