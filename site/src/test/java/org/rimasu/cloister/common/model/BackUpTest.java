package org.rimasu.cloister.common.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

/**
 * Test Unit For Backup
 * @author richards
 *
 */
public class BackUpTest {
	
	
	@Test
	public void canImportXml() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage()
				.getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object result = unmarshaller.unmarshal(getClass().getClassLoader()
				.getResource("backup.xml"));
		assertTrue(result instanceof BackUp);
		BackUp backUp = (BackUp) result;
		
		
		assertThat(backUp.getMembers().size(), is(3));
		
		Member member0 = backUp.getMembers().get(0);
		assertThat(member0.getFirstName(), is("Al'fr-e.d"));
		
		Member member1 = backUp.getMembers().get(1);
		assertThat(member1.getFirstName(), is("Catherine"));
		
		Member member2 = backUp.getMembers().get(2);
		assertThat(member2.getFirstName(), is("Richard"));
				
		assertNotNull(backUp);
		assertThat(backUp.getMessages().size(), is(1));
		
		Message message0 = backUp.getMessages().get(0);		
		assertThat(message0.getUuid(),is("0161c3c0-c288-11e0-962b-0800200c9a66"));
		assertThat(message0.getTitle(), is("Title content"));
		assertThat(message0.getContent(), is ("Content content"));
		assertThat(message0.getSender(), is(member0));		
		assertThat(message0.getRecipients().size(), is(2));
		assertThat(message0.getRecipients().get(0),is(member1));
		assertThat(message0.getRecipients().get(1),is(member2));	
		assertThat(message0.getStatus(), is(Message.Status.READ));
		//assertThat(message0.getSendDate());
	}


}
