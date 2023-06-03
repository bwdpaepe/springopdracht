package validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domein.BookForm;
import domein.Location;



public class BookFormLocation2Validation implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Location.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BookForm bookForm = (BookForm) target;
		if(bookForm.getLocationCode21() == 0 || bookForm.getLocationCode22() == 0) {
			return;
		}
		//if(bookForm.getLocationCode21() && bookForm.getLocationCode22()) {
			if(Math.abs(Math.abs(bookForm.getLocationCode21()) - Math.abs(bookForm.getLocationCode22())) < 50) {
				errors.rejectValue("locationCode22", 
						"location_code1code2",
						"");
			}
		//}
		
		
	}

	}
