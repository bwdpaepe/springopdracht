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
	
	@GetMapping(value = "/books")
	public List<Book> getBooksFromAuthor(@RequestParam String author_name) {
		return authorService.findByAuthor(author_name);
	}
	
	@GetMapping(value = "/book")
	public Book getBookByISBN(@RequestParam String isbn) {
	    return bookService.findByISBN(Long.parseLong(isbn));
	}

}
