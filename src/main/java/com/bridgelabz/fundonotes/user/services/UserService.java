package com.bridgelabz.fundonotes.user.services;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;

import com.bridgelabz.fundonotes.user.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.user.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.user.exceptions.UserNotFoundException;
import com.bridgelabz.fundonotes.user.models.ForgetPasswordDTO;
import com.bridgelabz.fundonotes.user.models.LoginDTO;
import com.bridgelabz.fundonotes.user.models.RegistrationDTO;


public interface UserService {

	public String login(LoginDTO loginDTO) throws LoginException;

	public void register(RegistrationDTO registrationdto) throws RegistrationException, MessagingException;

	public void activateAccount(String token) throws MessagingException, UserNotFoundException;

	public void forgotPassword(String email) throws MessagingException, FogetPasswordException;

	public void setNewPssword(ForgetPasswordDTO forgetPasswordDTO, String token) throws FogetPasswordException;

}
