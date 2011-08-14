package org.rimasu.cloister.server.backup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.rimasu.cloister.server.backup.Snapshot;
import org.rimasu.cloister.server.model.EntityTest;
import org.rimasu.cloister.server.model.Fixture;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.Message;

/**
 * Test Unit For Backup
 * @author richards
 *
 */
public class SnapshotTest extends EntityTest {

	@Test
	public void canImportXml() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage()
				.getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object result = unmarshaller.unmarshal(getClass().getClassLoader()
				.getResource("backup.xml"));
		assertTrue(result instanceof Snapshot);
		Snapshot backUp = (Snapshot) result;
		
		assertNotNull(backUp);
		assertThat(backUp.getMessages().size(), is(1));
		assertThat(backUp.getMessageBoxes().size(), is(6));			
		assertThat(backUp.getMembers().size(), is(3));
		assertThat(backUp.getPrincipals().size(),is(4));
		
		Member member0 = backUp.getMembers().get(0);
		assertThat(member0.getFirstName(), is("Al'fr-e.d"));		
		assertThat(member0.getInbox(), is(backUp.getMessageBoxes().get(0)));
		assertThat(member0.getSentItems(), is(backUp.getMessageBoxes().get(1)));
		
		Member member1 = backUp.getMembers().get(1);
		assertThat(member1.getFirstName(), is("Catherine"));
		assertThat(member1.getInbox(), is(backUp.getMessageBoxes().get(2)));
		assertThat(member1.getSentItems(), is(backUp.getMessageBoxes().get(3)));
		
		Member member2 = backUp.getMembers().get(2);
		assertThat(member2.getFirstName(), is("Richard"));
		assertThat(member2.getInbox(), is(backUp.getMessageBoxes().get(4)));
		assertThat(member2.getSentItems(), is(backUp.getMessageBoxes().get(5)));
						
		Message message0 = backUp.getMessages().get(0);		
		assertThat(message0.getId(),is("0161c3c0-c288-11e0-962b-0800200c9a66"));
		assertThat(message0.getTitle(), is("Title content"));
		assertThat(message0.getContent(), is ("Content content"));
		assertThat(message0.getSender(), is(member0));		
		assertThat(message0.getRecipients().size(), is(2));
		assertThat(message0.getRecipients().get(0),is(member1));
		assertThat(message0.getRecipients().get(1),is(member2));	
		assertThat(message0.getStatus(), is(Message.Status.READ));
		//assertThat(message0.getSendDate());
	}
	
	@Test
	public void canValidateBackUp()
	{
		Snapshot backUp = new Snapshot();
		Member member = Fixture.createMember();
		backUp.getMessageBoxes().addAll(member.getMessageBoxes());
		backUp.getMembers().add(member);
		
		assertValid(backUp);
		// make member first name invalid.
		member.setFirstName("");
		
		assertInvalid(member,"firstName");
		assertInvalid(backUp,"members[0].firstName");
	}


}
