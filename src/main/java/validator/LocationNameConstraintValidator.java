package validator;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LocationNameConstraintValidator implements ConstraintValidator<ValidLocationName, String>{

	@Override
    public void initialize(ValidLocationName constraintAnnotation) {}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
				
		return isLocationName(value);
	}
	
	public boolean isLocationName(String value) {
		
		if( value == null) {
			return true;	//blank location name is allowed
		}
		
		if (!value.matches("^[a-zA-Z]*")) {
			return false;
		}
		
		
		
		return true;
	}
	
	

}
