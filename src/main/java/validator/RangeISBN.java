package validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = RangeISBNConstraintValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface RangeISBN {
	String message() default "Formaat ISBN is ongeldig";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
