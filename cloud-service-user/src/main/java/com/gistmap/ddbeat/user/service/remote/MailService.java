package com.gistmap.ddbeat.user.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangran
 * @date 2018/7/25
 */
@FeignClient("mail")
public interface MailService {

    @RequestMapping(path = "/mail/send", method = RequestMethod.GET)
    void sendMail(@RequestParam("body") String body);
}
