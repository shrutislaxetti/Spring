package com.bridgelabz.fundonotes.usermodule.service;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.usermodule.exceptions.ActivationException;
import com.bridgelabz.fundonotes.usermodule.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.usermodule.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.usermodule.model.ForgetPasswordDTO;
import com.bridgelabz.fundonotes.usermodule.model.LoginDTO;
import com.bridgelabz.fundonotes.usermodule.model.Mail;
import com.bridgelabz.fundonotes.usermodule.model.RegistrationDTO;
import com.bridgelabz.fundonotes.usermodule.model.User;
import com.bridgelabz.fundonotes.usermodule.repository.UserRepository;
import com.bridgelabz.fundonotes.usermodule.util.Utility;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emialservive;
	/*@Autowired
	private */

	@Value("${link}")
	private String link;

	@Value("${link1}")
	private String link1;

	public String login(LoginDTO loginDTO) throws LoginException {

		Utility.validateLoginDetails(loginDTO);

		Optional<User> optionaluser = repository.findByEmail(loginDTO.getEmail());
		System.out.println(optionaluser);

		if (optionaluser.isPresent()) {

			if (passwordEncoder.matches(loginDTO.getPassword(), optionaluser.get().getPassword())) {

				if (optionaluser.get().isStatus()) {

					User user = new User();
					user.setEmail(loginDTO.getEmail());
					return Utility.tokenGenerator(user.getEmail());

				} else {
					throw new LoginException("Incorrect Password Exception");
				}
			}
		}

		throw new LoginException("Login Unsuccessfull");
	}

	@Override
	public void register(RegistrationDTO registrationDTO) throws RegistrationException, MessagingException {

		Utility.validateUserDetails(registrationDTO);
		repository.findByUserName(registrationDTO.getUserName());
		Optional<User> optinaluser = repository.findByUserName(registrationDTO.getUserName());
		User user = new User();
		if (optinaluser.isPresent()) {
			throw new RegistrationException("User with this name already present!!! Try to Register again..  ");
		}

		user.setUserName(registrationDTO.getUserName());
		user.setEmail(registrationDTO.getEmail());
		user.setContactNum(registrationDTO.getContactNum());

		String hashedPassword = passwordEncoder.encode(registrationDTO.getPassword());

		user.setPassword(hashedPassword);
		repository.save(user);
		Mail mail = new Mail();
		String token = Utility.tokenGenerator(user.getId());
		mail.setBody(link + token);
		mail.setSubject("Account activated");
		mail.setTo(user.getEmail());
		emialservive.sendActivationLink(mail);
	}

	public void activateAccount(String token) throws AddressException, MessagingException, ActivationException {

		String email = Utility.parseJWT(token);

		Optional<User> user = repository.findById(email);
		if (!user.isPresent()) {
			throw new ActivationException("Failed to Activate the User");
		}

		User userobj = new User();
		userobj.setStatus(true);
		userobj.setUserName(user.get().getUserName());
		userobj.setContactNum(user.get().getContactNum());
		userobj.setEmail(user.get().getEmail());
		userobj.setPassword(user.get().getPassword());
		repository.save(userobj);

	}

	@Override
	public void forgotPassword(String email) throws MessagingException, FogetPasswordException {
		System.out.println(email);
		Mail mail = new Mail();
		String token = Utility.tokenGenerator(email);
		mail.setBody(link1 + token);
		mail.setSubject("Account activated");
		mail.setTo(email);
		emialservive.sendActivationLink(mail);
	}

	@Override
	public void setNewPssword(ForgetPasswordDTO forgetPasswordDTO, String token) throws FogetPasswordException {

		String email = Utility.parseJWT(token);
		
		Optional<User> optinaluser = repository.findById(email);
		System.out.println(optinaluser);
		if (!optinaluser.isPresent()) {
			throw new FogetPasswordException("Failed to reset password");
		}
		User user = new User();
		Utility.validateFogetPasswordDetails(forgetPasswordDTO);
		String hashedPassword = passwordEncoder.encode(forgetPasswordDTO.getPassword());
		user.setPassword(hashedPassword);
		user.setUserName(optinaluser.get().getUserName());
		user.setContactNum(optinaluser.get().getContactNum());
		user.setEmail(optinaluser.get().getEmail());
		user.setStatus(true);
		repository.save(user);

	}
}