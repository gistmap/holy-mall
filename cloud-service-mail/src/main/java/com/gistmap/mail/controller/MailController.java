package com.gistmap.mail.controller;

import com.gistmap.mail.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangran
 * @date 2018/7/25
 */
@RestController
public class MailController {

    @Autowired
    private Producer producer;

    @GetMapping("/mail/send")
    public String send(String body) {
        producer.send(body);
        return "success";
    }
}
