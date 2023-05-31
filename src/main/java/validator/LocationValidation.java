package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domein.Location;

public class LocationValidation implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Location.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Location location = (Location) target;
		
		if(Math.abs(Math.abs(location.getCode1()) - Math.abs(location.getCode2())) < 50) {
			errors.rejectValue("code2", 
					"location_code1code2",
					"");
		}
		
	}

}
