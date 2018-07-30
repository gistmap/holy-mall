package com.gistmap.mail.service;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangran
 */
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Value("${spring.mail.username}")
	private String sender;

	public void sendTemplateMail(String to, String name){
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(to);
			helper.setSubject("主题：模板邮件");

			Map<String, Object> model = new HashMap<>();
			model.put("name", name);
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			helper.setText(html, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}



}
