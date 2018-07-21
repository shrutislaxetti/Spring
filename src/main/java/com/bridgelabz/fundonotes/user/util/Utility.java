package com.bridgelabz.fundonotes.user.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.login.LoginException;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;

import com.bridgelabz.fundonotes.user.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.user.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.user.models.ForgetPasswordDTO;
import com.bridgelabz.fundonotes.user.models.LoginDTO;
import com.bridgelabz.fundonotes.user.models.RegistrationDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utility {

	private Utility() {

	}

	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.host}")
	private String host;

	private static final Pattern EMAIL_REGEX = Pattern
			.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);

	private static final Pattern PASSWORD_REGEX = Pattern
			.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");

	private static final Pattern CONTACT_REGEX = Pattern.compile("^[0-9]{10}$");

	private static Matcher matcher;

	public static boolean validateEmail(String email) {
		matcher = EMAIL_REGEX.matcher(email);

		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean validatePassword(String password) {
		matcher = PASSWORD_REGEX.matcher(password);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean validateContactNum(String contactNum) {
		matcher = CONTACT_REGEX.matcher(contactNum);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static void validateUserDetails(RegistrationDTO registrationDTO) throws RegistrationException {

		if (registrationDTO.getUserName() == null || registrationDTO.getUserName().length() < 3) {
			throw new RegistrationException("User Name should not be less than 3 characters");
		}

		if (registrationDTO.getEmail() == null || (!validateEmail(registrationDTO.getEmail()))) {
			throw new RegistrationException("EmailField is empty or Invalid EmailId !!");
		}

		if (registrationDTO.getContactNum() == null || (!validateContactNum(registrationDTO.getContactNum()))) {
			throw new RegistrationException("Contactnumber fileld is Empty or Number should be 10 Numbers");
		}

		if (registrationDTO.getPassword() == null || (!validatePassword(registrationDTO.getPassword()))) {
			throw new RegistrationException(
					"Invalid Password !!..Password must contain atleat 1 special character,Upper_case letter nad minimum of 8 characters and numbres within it");
		}
		if (!(registrationDTO.getPassword().equals(registrationDTO.getConfirmpassword()))) {
			throw new RegistrationException("Password should match with confirm Password");
		}

	}

	public static void validateLoginDetails(LoginDTO loginDTO) throws LoginException {

		if (loginDTO.getEmail() == null || (!validateEmail(loginDTO.getEmail()))) {
			throw new LoginException("EmailField is empty or Invalid EmailId !!");
		}

		if (loginDTO.getPassword() == null || (!validatePassword(loginDTO.getPassword()))) {
			throw new LoginException(
					"Invalid Password !!..Password must contain atleat 1 special character,Upper_case letter nad minimum of 8 characters and numbres within it");
		}

	}

	public static String tokenGenerator(String id) {
		String key = "shruti";
		long nowMillis = System.currentTimeMillis() + (20 * 60 * 60 * 1000);
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(id).signWith(SignatureAlgorithm.HS256,
				key);

		return builder.compact();
	}

	public static String parseJWT(String jwt) {
		String key = "shruti";
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
				.getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		return claims.getId();

	}

	public static void validateFogetPasswordDetails(ForgetPasswordDTO forgetPasswordDTO) throws FogetPasswordException {
		
		if (forgetPasswordDTO.getPassword() == null || (!validatePassword(forgetPasswordDTO.getPassword()))) {
			throw new FogetPasswordException(
					"Invalid Password !!..Password must contain atleat 1 special character,Upper_case letter nad minimum of 8 characters and numbres within it");
		}
		
		if (!(forgetPasswordDTO.getPassword().equals(forgetPasswordDTO.getConfirmpassword()))) {
			throw new FogetPasswordException("Password should match with confirm Password");
		}
	}

}
