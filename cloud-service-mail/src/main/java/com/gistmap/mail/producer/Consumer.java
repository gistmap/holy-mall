package com.gistmap.mail.producer;

import com.gistmap.mail.common.JsonKeyReader;
import com.gistmap.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	@Autowired
	private MailService mailService;
	
	@JmsListener(destination = "email.queue")
	public void receiveQueue(String body) {
		System.out.println("消费消息：" + body);
        JsonKeyReader reader = new JsonKeyReader(body);
        String to = reader.readString("to", false);
        String name = reader.readString("name", false);
        mailService.sendTemplateMail(to, name);
	}

}

