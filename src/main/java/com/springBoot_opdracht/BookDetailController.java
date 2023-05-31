package com.springBoot_opdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import domein.Book;
import jakarta.validation.Valid;
import validator.LocationValidation;

@Controller
public class BookDetailController {
	
	@Autowired
	private LocationValidation locationValidation;
	
	@GetMapping(value = "/book")
	public String bookDetail(@Valid Book book, BindingResult result, Model model, Authentication authentication) {
		locationValidation.validate(book, result);
		
		if(result.hasErrors()) {
			return "welcome";
		}
		
		return "book_detail";
	}

}
