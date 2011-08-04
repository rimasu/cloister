package org.rimasu.cloister.common.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.hamcrest.Matcher;
import org.junit.Test;

public class InterestTest {
	
	private static final String EXAMPLE_CONTENT = "example content";

	@Test
	public void canConvertXmlToMember() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage()
				.getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object result = unmarshaller.unmarshal(getClass().getClassLoader()
				.getResource("interest.xml"));
		assertTrue(result instanceof Interest);
		Interest interest = (Interest) result;		
		assertThat(interest.getContent(), is(EXAMPLE_CONTENT));
	}

	@Test
	public void canConvertMemberToXml() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage()
				.getName());
		Marshaller marshaller = context.createMarshaller();
		Interest interest = new Interest(EXAMPLE_CONTENT);
		StringWriter dest = new StringWriter();
		marshaller.marshal(interest, dest);
		dest.close();
		String content = dest.toString();		
		assertTrue(content.contains(EXAMPLE_CONTENT));
	}
}
