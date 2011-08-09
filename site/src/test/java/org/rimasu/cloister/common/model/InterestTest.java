package org.rimasu.cloister.common.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public class InterestTest {
	
	private String UPDATED_CONTENT = "updated content.";
	private String DIRTY_XML="<h1>Simon</h1><script></script>";
	private String CLEAN_XML="<h1>Simon</h1>&lt;script&gt;&lt;/script&gt;";
	private Validator validator;
		

	@Before
	public void beforeEachTest() {
		ValidatorFactory factory = Validation.byDefaultProvider().configure()
				.buildValidatorFactory();
		validator = factory.getValidator();
	}


	@Test
	public void canChangeContent(){
		Interest interest = new Interest();		
		assertThat(interest.getContent(), is (""));		
		interest.setContent(UPDATED_CONTENT);		
		assertThat(interest.getContent(), is(UPDATED_CONTENT));
	}
		
	@Test
	public void contentIsSantized()
	{
		Interest interest = new Interest();
		interest.setContent(DIRTY_XML);		
		assertThat(interest.getContent(),is(CLEAN_XML));
	}
	
	@Test
	public void emptyInterestIsInvalid()
	{
		Interest interest = new Interest();		
		assertContentIsInvalid(interest);
	}
	
	@Test
	public void nullInterestIsInvalid()
	{
		Interest interest = new Interest();	
		interest.setContent(null);
		assertContentIsInvalid(interest);
	}



	private void assertContentIsInvalid(Interest interest) {
		Set<ConstraintViolation<Interest>> reports = validator.validate(interest);
		assertNotNull(reports);
		assertThat(reports.size(), is(1));
		ConstraintViolation<Interest> report = reports.iterator().next();
		assertThat(report.getPropertyPath().toString(), is("content"));
		assertThat(report.getRootBean(), is(interest));
		System.out.println(report.getMessage());
	}
	
}
