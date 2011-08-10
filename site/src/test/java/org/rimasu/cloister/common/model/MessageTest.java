package org.rimasu.cloister.common.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

/**
 * Test unit for message.
 */
public class MessageTest {
	
	
	private static final String MODIFIED_TITLE = "modified title.";
	
	private static final String MODIFIED_CONTENT = "modified content.";
	
	@Test
	public void canChangeTitle()
	{
		Message message = new Message();		
		message.setTitle(MODIFIED_TITLE);	
		assertThat(message.getTitle(), is(MODIFIED_TITLE));
	}	
	
	@Test
	public void canChangeContent()
	{
		Message message = new Message();		
		message.setContent(MODIFIED_CONTENT);	
		assertThat(message.getContent(), is(MODIFIED_CONTENT));
	}
	
}
