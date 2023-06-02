package validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domein.BookForm;
import domein.Location;



public class BookFormLocation1Validation implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Location.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BookForm bookForm = (BookForm) target;
		
		if(Math.abs(Math.abs(bookForm.getLocationCode11()) - Math.abs(bookForm.getLocationCode12())) < 50) {
			errors.rejectValue("locationCode12", 
					"location_code1code2",
					"");
		}
		
		
	}

	}
