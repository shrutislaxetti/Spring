package com.bridgelabz.fundonotes.user.services;

import javax.mail.MessagingException;

import com.bridgelabz.fundonotes.user.models.MailDTO;

public interface EmailService {
	
	 void sendMail(MailDTO mail) throws MessagingException;
}
