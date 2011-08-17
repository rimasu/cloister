package org.rimasu.cloister.server.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.security.SecureRandom;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.rimasu.cloister.server.model.auth.Principal;

public class EntityTest {
	
	private static Validator validator;
	
	@BeforeClass
	public static void beforeAllTests() {
		ValidatorFactory factory = Validation.byDefaultProvider().configure()
				.buildValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Before
	public void beforeEachTest()
	{
		Fixture.reset();		
		Principal.resetSaltSeed();
	}
	
		
	protected <E> void assertValid(E entity){
		Set<ConstraintViolation<E>> result = validator.validate(entity);		
		assertTrue(result.isEmpty());
	}
	
	protected <E> void assertInvalid(E entity, String invalidField)
	{
		Set<ConstraintViolation<E>> result = validator.validate(entity);
		assertThat(result.size(), is(1));
		assertThat(result.iterator().next().getPropertyPath().toString(), is(invalidField));
	}

}
