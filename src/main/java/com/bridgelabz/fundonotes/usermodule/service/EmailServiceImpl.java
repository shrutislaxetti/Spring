package com.bridgelabz.fundonotes.usermodule.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundonotes.usermodule.model.Mail;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	@Value("${link}")
	private String link;

	public void sendActivationLink(Mail mail) throws MessagingException {
	
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(mail.getSubject());
		helper.setTo(mail.getTo());
		helper.setText(mail.getBody());
		emailSender.send(message);
	}

}
