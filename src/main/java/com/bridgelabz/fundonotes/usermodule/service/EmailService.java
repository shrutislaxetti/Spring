package com.bridgelabz.fundonotes.usermodule.service;

import javax.mail.MessagingException;

import com.bridgelabz.fundonotes.usermodule.model.Mail;

public interface EmailService {
	
	public void sendActivationLink(Mail mail) throws MessagingException;
}
