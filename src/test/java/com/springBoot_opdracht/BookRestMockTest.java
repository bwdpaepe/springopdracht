package com.springBoot_opdracht;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.springBoot_opdracht.BookRestController;

import domein.Book;
import domein.BookRest;
import service.AuthorService;
import service.BookService;

@SpringBootTest
class BookRestMockTest {

	@Mock 
	private BookService mockBookService;
	
	@Mock 
	private AuthorService mockAuthorService;
	
	private BookRestController controller;
	private MockMvc mockMvc;
	
	private final long ID = 1;
	private final String NAME = "Romeo and Juliet";
	private final String IMAGE = "https://www.gutenberg.org/cache/epub/1513/pg1513.cover.medium.jpg";
	private final long ISBN = 9787698540261L;
	private final String AUTHOR = "Shakespeare, William";
	private final String LOCATION = "locationA";
	private final String VOTE = "sandra@example.com";
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new BookRestController();
		mockMvc = standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "bookService", mockBookService);
		ReflectionTestUtils.setField(controller, "authorService", mockAuthorService);
	}
	
	private BookRest aBook() {
		List<String> authors = new ArrayList<>();
		List<String> locations = new ArrayList<>();
		List<String> users = new ArrayList<>();
		authors.add("Shakespeare, William");
		locations.add("locationA");
		users.add("sandra@example.com");
		BookRest aBook = new BookRest(1,"Romeo and Juliet", "https://www.gutenberg.org/cache/epub/1513/pg1513.cover.medium.jpg", 9787698540261L, 21.215347427581065, authors, locations, users);
		return aBook;
	}
	
	private void performRest(String uri) throws Exception {
		mockMvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.book_id").value(ID))
		.andExpect(jsonPath("$.name").value(NAME))
		.andExpect(jsonPath("$.ISBN").value(ISBN));
	}
	
	@Test
	public void testBookByISBN_isOk() throws Exception {
		Mockito.when(mockBookService.createDummyBook()).thenReturn(aBook());
		performRest("/rest/book?isbn=9787698540261");
		Mockito.verify(mockBookService).createDummyBook();
		
	}
	
	@Test
	public void testBooksFromAuthor_isOk() throws Exception {
		Mockito.when(mockAuthorService.findByAuthor(AUTHOR)).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/rest/books?author_name=Shakespeare, William"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isEmpty());

		Mockito.verify(mockAuthorService).findByAuthor(AUTHOR);
	}

	
}
