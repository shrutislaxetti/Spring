package com.bridgelabz.fundonotes.user.services;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundonotes.user.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.user.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.user.exceptions.UserNotFoundException;
import com.bridgelabz.fundonotes.user.models.ForgetPasswordDTO;
import com.bridgelabz.fundonotes.user.models.LoginDTO;
import com.bridgelabz.fundonotes.user.models.MailDTO;
import com.bridgelabz.fundonotes.user.models.RegistrationDTO;
import com.bridgelabz.fundonotes.user.models.User;
import com.bridgelabz.fundonotes.user.rabbitmq.Producer;
import com.bridgelabz.fundonotes.user.repositories.UserRepository;
import com.bridgelabz.fundonotes.user.util.Utility;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emialservive;

	@Autowired
	private Producer producer;

	@Value("${Activationlink}")
	private String link;

	@Value("${ResetPasswordlink}")
	private String link1;

	public String login(LoginDTO loginDTO) throws LoginException {

		Utility.validateLoginDetails(loginDTO);

		Optional<User> optionaluser = repository.findByEmail(loginDTO.getEmail());

		if (!optionaluser.isPresent()) {

			throw new LoginException("Login Unsuccessfull...User Not Present");

		}
		if (!optionaluser.get().isStatus()) {
			throw new LoginException("Please Activate user");
		}
		if (!(passwordEncoder.matches(loginDTO.getPassword(), optionaluser.get().getPassword()))) {

			throw new LoginException("Incorrect Password Exception");

		}
		return Utility.tokenGenerator(optionaluser.get().getId());
	}

	@Override
	public void register(RegistrationDTO registrationDTO) throws RegistrationException, MessagingException {

		Utility.validateUserDetails(registrationDTO);
		Optional<User> optinaluser = repository.findByEmail(registrationDTO.getEmail());

		if (optinaluser.isPresent()) {
			throw new RegistrationException("User with this Email already present!!! Try to Register again..  ");
		}

		User user = new User();
		user.setUserName(registrationDTO.getUserName());
		user.setEmail(registrationDTO.getEmail());
		user.setContactNum(registrationDTO.getContactNum());

		String hashedPassword = passwordEncoder.encode(registrationDTO.getPassword());

		user.setPassword(hashedPassword);
		repository.save(user);

		MailDTO mail = new MailDTO();
		String token = Utility.tokenGenerator(user.getId());
		mail.setBody(link + token);
		mail.setSubject("Account activated");
		mail.setTo(user.getEmail());
		emialservive.sendMail(mail);
	
		 //producer.produce(mail);
		 

	}

	public void activateAccount(String token) throws MessagingException, UserNotFoundException {

		String id = Utility.parseJWT(token);

		Optional<User> user = repository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("Failed to Activate the User");
		}

		User userobj = user.get();
		userobj.setStatus(true);
		repository.save(userobj);

	}

	@Override
	public void forgotPassword(String email) throws MessagingException, FogetPasswordException {

		MailDTO mail = new MailDTO();
		String token = Utility.tokenGenerator(email);
		mail.setBody(link1 + token);
		mail.setSubject("Account activated");
		mail.setTo(email);
		emialservive.sendMail(mail);
	}

	@Override
	public void setNewPssword(ForgetPasswordDTO forgetPasswordDTO, String token) throws FogetPasswordException, UserNotFoundException {

		String email = Utility.parseJWT(token);

		Optional<User> optinaluser = repository.findById(email);

		if (!optinaluser.isPresent()) {
			throw new UserNotFoundException("Failed to reset password");
		}
	
		User user = new User();
		Utility.validateFogetPasswordDetails(forgetPasswordDTO);
		String hashedPassword = passwordEncoder.encode(forgetPasswordDTO.getPassword());
		user.setPassword(hashedPassword);
		user.setStatus(true);
		repository.save(user);

	}
}