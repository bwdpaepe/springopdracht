package validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ISBNConstrainValidatorTest {
	
	ISBNConstraintValidator isbn;
	
	@BeforeEach
	public void before() {
		isbn = new ISBNConstraintValidator();
	}

	@ParameterizedTest
	@ValueSource(longs = {9789027439642L})
	void isISBN_expectTrue(long number) {
		assertTrue(isbn.isISBN(number));
	}
	
	@ParameterizedTest
	@ValueSource(longs = {9789027439643L})
	void isISBN_expectFalse(long number) {
		assertFalse(isbn.isISBN(number));
	}

}
