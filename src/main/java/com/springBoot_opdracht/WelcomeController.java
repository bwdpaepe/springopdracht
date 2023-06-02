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
import domein.BookForm;
import domein.Location;
import jakarta.validation.Valid;
import service.BookService;
import service.LocationService;
import utility.Message;
import validator.BookFormLocation1Validation;
import validator.BookFormLocation2Validation;
import validator.BookFormLocation3Validation;
import validator.LocationValidation;

@Controller
@RequestMapping("/welcome")
//@SessionAttributes("authentication")
public class WelcomeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired 
	private MessageSource messageSource;
	
	@Autowired
	private LocationValidation locationValidation;
	
	@Autowired
	private BookFormLocation1Validation bfl1Validation;
	@Autowired
	private BookFormLocation2Validation bfl2Validation;
	@Autowired
	private BookFormLocation3Validation bfl3Validation;
	
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
	public String onSubmit(@Valid BookForm bookForm, BindingResult result, Model model, Locale locale) {
		bfl1Validation.validate(bookForm, result);
		if(bookForm.getLocationName2()!= null) {
			System.out.printf("code21: %d%n",bookForm.getLocationCode21());
			bfl2Validation.validate(bookForm, result);
		}
		if(bookForm.getLocationName3()!= null) {
			bfl3Validation.validate(bookForm, result);
		}
		if (result.hasErrors()) {
			model.addAttribute("message",
					new Message("error",
							messageSource.getMessage("book_save_fail",
									new Object[]{}, locale)));
			return "book_form";
		}
		
		//System.out.println(bookForm.getAuthor1());
		
		
				
		Book book = new Book(
				bookForm.getName(),
				bookForm.getImage(),
				bookForm.getIsbn(),
				bookForm.getPrice()
				);
		book.addAuthor(bookForm.getAuthor1());
		if(bookForm.getAuthor2()!= null) {
			book.addAuthor(bookForm.getAuthor2());
		}
		if(bookForm.getAuthor3()!= null) {
			book.addAuthor(bookForm.getAuthor3());
		}
		Location location = new Location(bookForm.getLocationName1(),bookForm.getLocationCode11(),bookForm.getLocationCode12());
		locationService.save(location);
		book.addLocation(locationService.findByName(bookForm.getLocationName1()));
		if(bookForm.getLocationName2()!= null) {
			location = new Location(bookForm.getLocationName2(),bookForm.getLocationCode21(),bookForm.getLocationCode22());
			locationService.save(location);
			
			book.addLocation(locationService.findByName(bookForm.getLocationName2()));
		}
		if(bookForm.getLocationName3()!= null) {
			location = new Location(bookForm.getLocationName3(),bookForm.getLocationCode31(),bookForm.getLocationCode32());
			locationService.save(location);

			book.addLocation(locationService.findByName(bookForm.getLocationName3()));
		}
		
		bookService.save(book);
		model.addAttribute("message",
				new Message("success",
						messageSource.getMessage("book_save_success",
								new Object[]{}, locale)));
		return "redirect:/welcome";
	}
	

}
