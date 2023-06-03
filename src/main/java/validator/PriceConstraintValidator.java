package validator;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceConstraintValidator implements ConstraintValidator<ValidPrice, Double>{

	@Override
    public void initialize(ValidPrice constraintAnnotation) {}
	
	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
				
		return isPrice(value);
	}
	
	public boolean isPrice(Double value) {
		
		if( (value == null) || (value == 0.0)) {
			return true;	//blank price is allowed
		}
		
		if( (value < 0.01) || (value > 99.9)) {
			return false;
		}
		
		
		
		return true;
	}
	
	

}
