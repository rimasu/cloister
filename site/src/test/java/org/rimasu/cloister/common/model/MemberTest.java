package org.rimasu.cloister.common.model;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

/**
 * Test unit for {@link Member}.
 * @author richards
 *
 */
public class MemberTest {
	
	private static final String TEST_PERSISTENCE_UNIT = "TEST_UNIT";
	
	// properties values used in getter/setter testing.
	private static final String ALT_FIRST_NAME = "alternative_first_name";
	private static final String ALT_UUID = "0237eee0-bc3a-11e0-962b-0800200c9a66";
	
	// properties stored in example xml file.
	private static final String XML_FIRST_NAME = "Alfred";	
	private static final String XML_UUID = "36d49a30-bc3b-11e0-962b-0800200c9a66";

	@Test
	public void canChangeMembersFirstName()
	{
		Member member = new Member();
		member.setFirstName(ALT_FIRST_NAME);
		assertThat(member.getFirstName(), is (ALT_FIRST_NAME));		
	}
	
	@Test
	public void canChangeAMembersUuid()
	{
		Member member = new Member();
		member.setUuid(ALT_UUID);
		assertThat(member.getUuid(), is (ALT_UUID));		
	}
	
	@Test
	public void canConvertXmlToMember() throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object result = unmarshaller.unmarshal(getClass().getClassLoader().getResource("member.xml"));
		assertTrue(result instanceof Member);
		Member member = (Member)result;
		assertThat(member.getFirstName(), is(XML_FIRST_NAME));
		assertThat(member.getUuid(),is(XML_UUID));
	}
	
	@Test
	public void canConvertMemberToXml() throws JAXBException, IOException
	{
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage().getName());
		Marshaller marshaller = context.createMarshaller();
		Member member = new Member();
		member.setUuid(ALT_UUID);
		member.setFirstName(ALT_FIRST_NAME);
		StringWriter dest = new StringWriter();
		marshaller.marshal(member, dest);
		dest.close();
		String content = dest.toString();
		assertTrue(content.contains(ALT_UUID));
		assertTrue(content.contains(ALT_FIRST_NAME));
	}
	
	@Test
	public void canStoreMemberToEntityManager()
	{		 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(TEST_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		
		Member member = new Member();
		member.setUuid(ALT_UUID);
		member.setFirstName(ALT_FIRST_NAME);
		
		Member found = em.find(Member.class, ALT_UUID);
		assertNull(found);
		
		em.persist(member);
		
		found = em.find(Member.class, ALT_UUID);		
		assertNotNull(found);		
	}
	
	
}
