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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import domein.Book;
import domein.Bookform;
import domein.Location;
import jakarta.validation.Valid;
import service.BookService;
import service.LocationService;
import utility.Message;

@Controller
@RequestMapping("/welcome")
//@SessionAttributes("authentication")
public class WelcomeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired MessageSource messageSource;
	
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
	public String onSubmit(@Valid Bookform bookForm, BindingResult result, Model model, Locale locale) {
		//todo add locationvalidation
		if (result.hasErrors()) {
			model.addAttribute("message",
					new Message("error",
							messageSource.getMessage("book_save_fail",
									new Object[]{}, locale)));
			return "book_form";
		}
		
		System.out.println(bookForm.getAuthor1());
		Location location = new Location(bookForm.getLocationName1(),bookForm.getLocationCode11(),bookForm.getLocationCode12());
		locationService.save(location);
		location = new Location(bookForm.getLocationName2(),bookForm.getLocationCode21(),bookForm.getLocationCode22());
		locationService.save(location);
		//TODO add location3
		
		Book book = new Book(
				bookForm.getName(),
				bookForm.getImage(),
				bookForm.getIsbn(),
				bookForm.getPrice()
				);
		book.addAuthor(bookForm.getAuthor1());
		book.addAuthor(bookForm.getAuthor2());
		book.addAuthor(bookForm.getAuthor3());
		book.addLocation(locationService.findByName(bookForm.getLocationName1()));
		book.addLocation(locationService.findByName(bookForm.getLocationName2()));
		
		bookService.save(book);
		model.addAttribute("message",
				new Message("success",
						messageSource.getMessage("book_save_success",
								new Object[]{}, locale)));
		return "redirect:/welcome";
	}
	

}
