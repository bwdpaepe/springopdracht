package com.springBoot_opdracht;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import domein.Author;
import domein.Book;
import domein.BookForm;
import domein.BookVoted;
import domein.Location;
import domein.User;
import jakarta.validation.Valid;
import service.AuthorService;
import service.BookService;
import service.UserService;
import utility.Message;
import validator.LocationValidation;

@Controller
@RequestMapping("/book")
//@SessionAttributes("authentication")
public class BookDetailController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/{id}")
	public String bookDetail(@PathVariable("id") Long bookId, Model model, Authentication authentication) {
		
		// header
		
		List<String> listRoles = authentication.getAuthorities()
				                               .stream()
				                               .map(GrantedAuthority::getAuthority).toList();

        model.addAttribute("userName", authentication.getName());
        model.addAttribute("userListRoles", listRoles);
        
        // main
        
        Book book = bookService.findById(bookId);
        if(book == null) {
        	return "redirect:/welcome";
        }
        
        List<Author> authorsList = bookService.findAuthorsById(bookId);
        List<Location> locationsList = bookService.findLocationById(bookId);
        int numVotes = bookService.getNumVotes(bookId);
        
        User user = userService.findByEmail(authentication.getName());
        List<Book> booksOfUser = userService.booksOfUser(user.getId());
        
        if (booksOfUser.isEmpty()) {
        	model.addAttribute("vote", "up");
        }
        else if (booksOfUser.size() == user.getMaxVotes()) {
        	model.addAttribute("vote", "down");
        }
        else if(booksOfUser.contains(book)) {
        	model.addAttribute("vote", "down");
        }
        else {
        	model.addAttribute("vote", "up");
        }
        
        
        model.addAttribute("book", book);
        model.addAttribute("authorsList", authorsList);
        model.addAttribute("locationsList", locationsList);
        model.addAttribute("numVotes", numVotes);
        model.addAttribute("bookVoted", new BookVoted(bookId));

		return "book_detail";
	}
	
	@GetMapping(value = "/add")
    public String bookForm(Model model, Authentication authentication) {
		// header
		
			List<String> listRoles = authentication.getAuthorities()
					                               .stream()
					                               .map(GrantedAuthority::getAuthority).toList();

	        model.addAttribute("userName", authentication.getName());
	        model.addAttribute("userListRoles", listRoles);
	        
	    // main
	        
	        List<Author> authorList = authorService.findAll();
	        model.addAttribute("authorList", authorList);
	        model.addAttribute("bookForm", new BookForm());
	        return "book_form";
	}
	
	@PostMapping(value = "/voted/up")
	public String bookVotedUpSubmit(BookVoted bookVoted, BindingResult result, Model model, Authentication authentication) {
		
		Book book = bookService.findById(bookVoted.getBookId());
		book.addUser(userService.findByEmail(authentication.getName()));
		bookService.save(book);
		
		// header
		
		List<String> listRoles = authentication.getAuthorities()
				                               .stream()
				                               .map(GrantedAuthority::getAuthority).toList();

        model.addAttribute("userName", authentication.getName());
        model.addAttribute("userListRoles", listRoles);
        
        // list of books
        model.addAttribute("bookList", bookService.allBookRows());
		        
		return "hello";
	}
	
	@PostMapping(value = "/voted/down")
	public String bookVotedDownSubmit(BookVoted bookVoted, BindingResult result, Model model, Authentication authentication) {
		
		Book book = bookService.findById(bookVoted.getBookId());
		book.removeUser(userService.findByEmail(authentication.getName()));
		bookService.save(book);
		
		// header
		
		List<String> listRoles = authentication.getAuthorities()
				                               .stream()
				                               .map(GrantedAuthority::getAuthority).toList();

        model.addAttribute("userName", authentication.getName());
        model.addAttribute("userListRoles", listRoles);
        
        // list of books
        model.addAttribute("bookList", bookService.allBookRows());
		        
		return "hello";
	}
	
	
}
