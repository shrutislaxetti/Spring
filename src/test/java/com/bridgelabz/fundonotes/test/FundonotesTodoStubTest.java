package com.bridgelabz.fundonotes.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.fundonotes.FundoNotesApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FundoNotesApplication.class)
@SpringBootTest
public class FundonotesTodoStubTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	// @Test
	public void registerTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON).content(
				"{\"email\" : \"shrutilaxetti@gmail.com\", \"password\" : \"Mamta@222\",\"confirmpassword\":\"Mamta@222\",\"userName\":\"mamta\",\"contactNum\":\"9854667188\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("Registration Successfull!!"))
				.andExpect(jsonPath("$.status").value(201));
	}

	// @Test
	public void loginTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"email\" :\"shrutilaxetti@gmail.com\",\"password\":\"Shru@1234\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").value("Login Successfull"))
				.andExpect(jsonPath("$.status").value(201));
	}

	// @Test
	public void activateTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/activate").param("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJzaHJ1dGlsYXhldHRpQGdtYWlsLmNvbSIsImlhdCI6MTUzMjA2NzU5NCwic3ViIjoic2hydXRpbGF4ZXR0aUBnbWFpbC5jb20ifQ.Y04eVABJi93EX7VJtf6MUYo7Ycc8mD6zO3frSyUJeAo")
				.accept(MediaType.TEXT_PLAIN_VALUE)).andExpect(jsonPath("$.message").value("Account activated"))
				.andExpect(jsonPath("$.status").value(201));
	}

	// @Test
	public void forgetPasswordTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/forgotpassword").param("email", "shrutilaxetti@gmail.com")
				.content(MediaType.TEXT_PLAIN_VALUE)).andExpect(jsonPath("$.message").value("Password Reset Link Sent"))
				.andExpect(jsonPath("$.status").value(202));
	}

	// @Test
	public void resetPasswordTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/setpassword").contentType(MediaType.APPLICATION_JSON).param(
				"token",
				"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJzaHJ1dGlsYXhldHRpQGdtYWlsLmNvbSIsImlhdCI6MTUzMjA2NzU5NCwic3ViIjoic2hydXRpbGF4ZXR0aUBnbWFpbC5jb20ifQ.Y04eVABJi93EX7VJtf6MUYo7Ycc8mD6zO3frSyUJeAo")
				.content("{ \"password\" :\"Shru@1234\",\"confirmpassword\":\"Shru@1234\"}"))
				.andExpect(jsonPath("$.message").value("Resetting Password successfull"))
				.andExpect(jsonPath("$.status").value(201));
	}
}
