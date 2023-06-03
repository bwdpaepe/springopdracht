package com.springBoot_opdracht;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domein.Book;
import service.AuthorService;
import service.BookService;

@RestController
@RequestMapping(value = "/rest")
public class BookRestController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	// GET localhost:8080/rest/books?author_name=Shakespeare, William
	//[2m2023-06-03T16:08:50.934+02:00[0;39m [31mERROR[0;39m [35m16876[0;39m [2m---[0;39m [2m[nio-8080-exec-5][0;39m [36mo.a.c.c.C.[.[.[/].[dispatcherServlet]   [0;39m [2m:[0;39m Servlet.service() for servlet [dispatcherServlet] threw exception
    //
	//org.springframework.security.access.AccessDeniedException: Access Denied
	@GetMapping(value = "/books")
	public List<Book> getBooksFromAuthor(@RequestParam String author_name) {
		return authorService.findByAuthor(author_name);
	}
	
	// GET localhost:8080/rest/book?isbn=9787698540261
	//2023-06-03T16:01:17.941+02:00[0;39m [31mERROR[0;39m [35m16876[0;39m [2m---[0;39m [2m[nio-8080-exec-2][0;39m [36mo.a.c.c.C.[.[.[/].[dispatcherServlet]   [0;39m [2m:[0;39m Servlet.service() for servlet [dispatcherServlet] threw exception
	//
	//org.springframework.security.access.AccessDeniedException: Access Denied
	//Caused by: org.springframework.security.access.AccessDeniedException: Access Denied
	@GetMapping(value = "/book")
	public Book getBookByISBN(@RequestParam String isbn) {
	    return bookService.findByISBN(Long.parseLong(isbn));
	}

}
