package com.springBoot_opdracht;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringBootOpdrachtApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void loginGet() throws Exception {
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}
	
	@WithMockUser(username = "tania@example.com", roles = { "USER" })
	@Test
	public void testAccessWithUserRole() throws Exception {
		mockMvc.perform(get("/welcome"))
		.andExpect(status().isOk())
		.andExpect(view().name("hello"))
		.andExpect(model().attributeExists("userName"))
		.andExpect(model().attribute("userName", "tania@example.com"));
	}

	@WithMockUser(username = "tania@example.com", roles = { "NONE" })
	@Test
	public void testNoAccessWithWrongUserRole() throws Exception {
		mockMvc.perform(get("/welcome"))
		.andExpect(status().isForbidden());
	}
	
	@Test
	void testWrongPassword() throws Exception {
		mockMvc.perform(formLogin("/login")
				.user("email", "tania@example.com")
				.password("password", "wrongpassword"))
				.andExpect(status().isFound()) 
				.andExpect(redirectedUrl("/login?error"));
	}

	@Test
	void testCorrectPassword() throws Exception {
		mockMvc.perform(formLogin("/login")
				.user("email", "tania@example.com")
				.password("password", "pass"))
				.andExpect(status().isFound()) 
				.andExpect(redirectedUrl("/welcome"));
	}

	

}
