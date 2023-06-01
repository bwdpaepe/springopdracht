package com.springBoot_opdracht;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
class BookDetailControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username = "tania@example.com", roles = {"USER"})
   @Test
	void test() throws Exception {
		mockMvc.perform(get("/book/2"))
		.andExpect(status().isOk())
		.andExpect(view().name("book_detail"))
		.andExpect(model().attributeExists("userName"))
		.andExpect(model().attributeExists("userListRoles"))
		.andExpect(model().attributeExists("book"))
		.andExpect(model().attributeExists("authorsList"))
		.andExpect(model().attributeExists("locationsList"));
		
	}

}
