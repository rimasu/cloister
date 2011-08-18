package org.rimasu.cloister.server.model.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.assertion.DbUnitAssert;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rimasu.cloister.server.model.EntityTest;
import org.rimasu.cloister.server.model.Fixture;
import org.rimasu.cloister.server.model.core.BlockText;
import org.rimasu.cloister.server.model.core.Member;

/**
 * Test unit for {@link Member}.
 * 
 * @author richards
 * 
 */
public class MemberTest extends EntityTest {
	
	// properties values used in getter/setter testing.
	private static final String FIRST_NAME = "Al'fr-e.d";
	private static final String UUID = "51d04e30-bdd3-11e0-962b-0800200c9a66";
	private static final String UUID2 = "51d04e30-bdd3-11e9-962b-0800200c9a66";

	private static final String INTEREST1 = "Example interest text1";
	
	private static final String INTEREST2 = "Example interest text2";

	
	@Before
	public void beforeEachTest(){
		Fixture.reset();
	}

	@Test
	public void canChangeMembersFirstName() {
		Member member = new Member();
		member.setFirstName(FIRST_NAME);
		assertThat(member.getFirstName(), is(FIRST_NAME));
	}



	@Test
	public void canCreateAValidationReportFromAMember() {
		assertValid(Fixture.createMember());
	}



	@Test
	public void nullFirstNameIsReportedByValidationReport() {
		Member member = Fixture.createMember();
		member.setFirstName(null);
		assertInvalid(member, "firstName");
	}

	@Test
	public void firstNameWithSpaceIsReportedByValidationReport() {
		Member member = Fixture.createMember();
		member.setFirstName("Alfr ed");
		assertInvalid(member, "firstName");
	}

	@Test
	public void singleCharFristNameIsReportedByValidationReport() {
		Member member = Fixture.createMember();
		member.setFirstName("A");
		assertInvalid(member, "firstName");
	}

	@Test
	public void digitInFristNameIsReportedByValidationReport() {
		Member member = Fixture.createMember();
		member.setFirstName("Alfr3d");
		assertInvalid(member, "firstName");
	}

	@Test
	public void semicolonInFristNameIsReportedByValidationReport() {
		Member member = Fixture.createMember();
		member.setFirstName("Alfr;d");
		assertInvalid(member, "firstName");		
	}

	@Test
	public void firstNameCanContainDotDashAndApostrophe() {
		Member member = Fixture.createMember();
		member.setFirstName("Alfr'ed");
		assertValid(member);
		member.setFirstName("Alfr-ed");
		assertValid(member);
		member.setFirstName("Alfr.ed");
		assertValid(member);
	}

	@Test
	public void canChangeMembersInterests() {
		Member member =Fixture.createMember();
		assertNotNull(member.getInterests());
		assertThat(member.getInterests().size(), is(3));
		member.getInterests().add(new BlockText(INTEREST1));
		assertThat(member.getInterests().size(), is(4));
	}

}
