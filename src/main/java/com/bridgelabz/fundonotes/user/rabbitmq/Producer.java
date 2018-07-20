package com.bridgelabz.fundonotes.user.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.user.models.MailDTO;

@Service
public class Producer {

	private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	
	  @Value("${exchange}") 
	  private String exchange;
	  
	 @Value("${routingkey}")
	 private String routingkey;
	
	public void produce(MailDTO mail) {
	
		 LOGGER.info(RabbitMqConfig.exchanger + RabbitMqConfig.routingkey);

		amqpTemplate.convertAndSend(RabbitMqConfig.exchanger, RabbitMqConfig.routingkey, mail);
	
		
	}
}
