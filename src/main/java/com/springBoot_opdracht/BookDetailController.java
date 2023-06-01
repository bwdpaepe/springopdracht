package com.springBoot_opdracht;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domein.Author;
import domein.Book;
import domein.Location;
import jakarta.validation.Valid;
import service.BookService;
import validator.LocationValidation;

@Controller
@RequestMapping("/book")
public class BookDetailController {
	
	@Autowired
	private BookService bookService;
	
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
        
        model.addAttribute("book", book);
        model.addAttribute("authorsList", authorsList);
        model.addAttribute("locationsList", locationsList);
        

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
	        model.addAttribute("newBook", new Book());
	        return "book_form";
	}

}
