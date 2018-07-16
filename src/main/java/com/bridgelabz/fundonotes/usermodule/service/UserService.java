package com.bridgelabz.fundonotes.usermodule.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.login.LoginException;
import com.bridgelabz.fundonotes.usermodule.exceptions.ActivationException;
import com.bridgelabz.fundonotes.usermodule.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.usermodule.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.usermodule.model.ForgetPasswordDTO;
import com.bridgelabz.fundonotes.usermodule.model.LoginDTO;
import com.bridgelabz.fundonotes.usermodule.model.RegistrationDTO;

public interface UserService {

	public String login(LoginDTO loginDTO) throws LoginException;

	public void register(RegistrationDTO registrationdto) throws RegistrationException, MessagingException;

	public void activateAccount(String token) throws AddressException, MessagingException, ActivationException;

	public void forgotPassword(String email) throws MessagingException, FogetPasswordException;

	public void setNewPssword(ForgetPasswordDTO forgetPasswordDTO, String token) throws FogetPasswordException;

	
}
