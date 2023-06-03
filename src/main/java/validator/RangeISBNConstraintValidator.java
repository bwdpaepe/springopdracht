package validator;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeISBNConstraintValidator implements ConstraintValidator<RangeISBN, Long>{

	@Override
    public void initialize(RangeISBN constraintAnnotation) {}
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
				
		return isISBN(value);
	}
	
	public boolean isISBN(Long value) {
		
		if( (value == null) || (value == 0L)) {
			return true;	//blank isbn is allowed
		}
		
		if( (value < 1000000000000L) || (value > 9999999999999L)) {
			return false;
		}
		
		return true;
	}
	
	

}
