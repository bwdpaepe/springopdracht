package validator;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LocationCodeConstraintValidator implements ConstraintValidator<ValidLocationCode, Integer>{

	@Override
    public void initialize(ValidLocationCode constraintAnnotation) {}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
				
		return isLocationCode(value);
	}
	
	public boolean isLocationCode(Integer value) {
		
		if( (value == null) || (value == 0.0)) {
			return true;	//blank location code is allowed
		}
		
		if( (value < 50) || (value > 300)) {
			return false;
		}
		
		
		
		return true;
	}
	
	

}
