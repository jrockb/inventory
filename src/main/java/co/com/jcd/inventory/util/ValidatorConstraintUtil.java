package co.com.jcd.inventory.util;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidatorConstraintUtil {
	
	public static void validRequest(Object object) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraintValidations = validator.validate(object);
		if(!constraintValidations.isEmpty()) {
			throw new ConstraintViolationException(constraintValidations);
		}
	}

}
