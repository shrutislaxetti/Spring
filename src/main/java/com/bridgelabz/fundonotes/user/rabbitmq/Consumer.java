package com.bridgelabz.fundonotes.user.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.user.models.MailDTO;
import com.bridgelabz.fundonotes.user.services.EmailService;

@Service
public class Consumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	EmailService emailService;

	@RabbitListener(queues = RabbitMqConfig.queueName, containerFactory = "jsaFactory")
	public void recievedMessage(MailDTO mail) throws Exception {
		
		LOGGER.info("MAIL IN THE CONSUMER" + mail);
		//emailService.sendActivationLink(mail);

	}
}