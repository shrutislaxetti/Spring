package com.bridgelabz.fundonotes.user.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundonotes.user.models.MailDTO;
import com.bridgelabz.fundonotes.user.rabbitmq.Producer;


@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	@Value("${Activationlink}")
	private String link;
	
	@Autowired
	Producer producer;

	public void sendMail(MailDTO mail) throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(mail.getSubject());
		helper.setTo(mail.getTo());
		helper.setText(mail.getBody());
		emailSender.send(message);
		// producer.produce(mail);
	}

}
