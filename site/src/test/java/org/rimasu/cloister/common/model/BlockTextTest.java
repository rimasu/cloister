package org.rimasu.cloister.common.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BlockTextTest extends EntityTest {
	
	private String UPDATED_CONTENT = "updated content.";
	private String DIRTY_XML="<h1>Simon</h1><script></script>";
	private String CLEAN_XML="<h1>Simon</h1>&lt;script&gt;&lt;/script&gt;";

		
	@Test
	public void canChangeContent(){
		BlockText interest = new BlockText();		
		assertThat(interest.getContent(), is (""));		
		interest.setContent(UPDATED_CONTENT);		
		assertThat(interest.getContent(), is(UPDATED_CONTENT));
	}
		
	@Test
	public void contentIsSantized()
	{
		BlockText interest = new BlockText();
		interest.setContent(DIRTY_XML);		
		assertThat(interest.getContent(),is(CLEAN_XML));
	}
	
	@Test
	public void emptyInterestIsInvalid()
	{
		BlockText interest = new BlockText();		
		assertInvalid(interest, "content");		
	}
	
	@Test
	public void nullInterestIsInvalid()
	{
		BlockText interest = new BlockText();	
		interest.setContent(null);
		assertInvalid(interest, "content");
	}

}
