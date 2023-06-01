package validator;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNConstraintValidator implements ConstraintValidator<ValidISBN, Long>{

	@Override
    public void initialize(ValidISBN constraintAnnotation) {}
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
				
		return isISBN(value);
	}
	
	public boolean isISBN(Long value) {
		//System.out.println(value);
		boolean isISBN = false;
		int evenRay[] = digitToArray(value, true);
		//System.out.println(evenRay);
		int oddRay[] = digitToArray(value, false);
		//System.out.println(oddRay);
		
		// check
		int valueNumberOfDigits = String.valueOf(value).length();
		long divider = valueNumberOfDigits > 1 ? (long) Math.pow(10, valueNumberOfDigits - 1) : 1L;
		int check = (int) (value % 10);
		//System.out.println(divider);
		//System.out.println(check);
		//System.out.println(check);
		
		
		int evenMagic = Arrays.stream(evenRay).sum();
		evenMagic *= 3;
		
		int oddMagic = Arrays.stream(oddRay).sum();
		
		if((evenMagic + oddMagic + check) % 10 == 0) {
			isISBN = true;
		}
		
		return isISBN;
	}
	
	public int[] digitToArray(long value, boolean even) {
		//System.out.printf("value: %d%n",value);
		//System.out.println(even);
		int valueNumberOfDigits = String.valueOf(value).length();
		
		long divider = valueNumberOfDigits > 1 ? (long) Math.pow(10, valueNumberOfDigits - 1) : 1L;
		int dividerNumberOfDigits = String.valueOf(divider).length();
		
		int array[] = new int[valueNumberOfDigits / 2];
		int counter = 0;
		while (divider / 10L >= 1) {
			if(valueNumberOfDigits == dividerNumberOfDigits) {
				//System.out.printf("value: %d%n", value);
				//System.out.printf("divider: %d%n", divider);
				int currentDigit = (int) (value / divider);
				//System.out.printf("current: %d%n", currentDigit);
				//System.out.printf("counter: %d%n", counter);
				//System.out.printf("num digits: %d%n" ,valueNumberOfDigits);
				if((valueNumberOfDigits % 2 == 0) == even) {
					//System.out.println("add to array");
					array[counter] = currentDigit;
					counter++;
				}
				value = value % divider;
				
			}
			else {
				int currentDigit = 0;
				if(((valueNumberOfDigits+1) % 2 == 0) == even) {
					//System.out.println("add to array");
					array[counter] = currentDigit;
					counter++;
				}
			}
			
			valueNumberOfDigits = String.valueOf(value).length();
			divider /= 10L;
			dividerNumberOfDigits = String.valueOf(divider).length();
		}
		return array;
	}

}
