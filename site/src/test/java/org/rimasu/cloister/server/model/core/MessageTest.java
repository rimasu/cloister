package org.rimasu.cloister.server.model.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rimasu.cloister.server.model.core.Message;

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
