package com.springBoot_opdracht;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domein.Book;
import jakarta.validation.Valid;
import repository.BookRepository;
import service.BookService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository br;
	
	@GetMapping
    public String printWelcome(Model model, Authentication authentication) {
		
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
	
	@PostMapping
	public String onSubmit(@Valid Book book, BindingResult result) {
		//todo add locationvalidation
		if (result.hasErrors()) {
			return "book_form";
		}
		br.save(book);
		return "hello";
	}
	

}
