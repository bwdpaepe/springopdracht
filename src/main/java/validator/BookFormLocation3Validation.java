package validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domein.BookForm;
import domein.Location;



public class BookFormLocation3Validation implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Location.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BookForm bookForm = (BookForm) target;
		if(bookForm.getLocationCode31() == 0 || bookForm.getLocationCode32() == 0) {
			return;
		}
		
		//if(bookForm.getLocationCode31() && bookForm.getLocationCode32()) {
		if(Math.abs(Math.abs(bookForm.getLocationCode31()) - Math.abs(bookForm.getLocationCode32())) < 50) {
			errors.rejectValue("locationCode32", 
					"location_code1code2",
					"");
		}
	//}
		
		
	}

	}
