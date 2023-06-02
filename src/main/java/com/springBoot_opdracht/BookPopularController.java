package com.springBoot_opdracht;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import domein.Author;
import domein.BookForm;
import domein.BookPopular;
import service.BookService;

@Controller
public class BookPopularController {
	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "/book/popular")
    public String bookPopular(Model model, Authentication authentication) {
		// header
		
			List<String> listRoles = authentication.getAuthorities()
					                               .stream()
					                               .map(GrantedAuthority::getAuthority).toList();

	        model.addAttribute("userName", authentication.getName());
	        model.addAttribute("userListRoles", listRoles);
	        
	    // main
	        
	        List<BookPopular> popList = bookService.findPopularBooks();
	        //popList.forEach(System.out::println);
	        System.out.println(popList.get(0).getName());
	        model.addAttribute("popList", popList);
	        
	        return "book_popular";
	}
}
