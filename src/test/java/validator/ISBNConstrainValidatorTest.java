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
	@ValueSource(longs = {9789027439642L,9787698540261L,9789731551746L,9784372349240L,9787955204509L,9781004196418L,9784357966899L,9787063486026L,9781472299406L,9781653539727L,9786014937723L,9780840034014L,9783674920188L,9783971380401L,9784674696820L,9785600534018L,9784172016151L,9788620272328L,9781938896705L,9781826873825L,9786230193088L})
	void isISBN_expectTrue(long number) {
		assertTrue(isbn.isISBN(number));
	}
	
	
	@ParameterizedTest
	@ValueSource(longs = {9789027439643L})
	void isISBN_expectFalse(long number) {
		assertFalse(isbn.isISBN(number));
	}

}
