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

	private static final String TEST_PERSISTENCE_UNIT = "TEST_UNIT";

	// properties values used in getter/setter testing.
	private static final String FIRST_NAME = "Al'fr-e.d";
	private static final String UUID = "51d04e30-bdd3-11e0-962b-0800200c9a66";
	private static final String UUID2 = "51d04e30-bdd3-11e9-962b-0800200c9a66";

	private static final String INTEREST1 = "Example interest text1";
	
	private static final String INTEREST2 = "Example interest text2";

	private DatabaseConnection connection;

	
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
		assertThat(member.getInterests().size(), is(0));
		member.getInterests().add(new BlockText(INTEREST1));
		assertThat(member.getInterests().size(), is(1));
	}


	@Test
	public void canLoadMemberViaJpa() throws SQLException,
			DatabaseUnitException, IOException {
		// need to create the entity manager to ensure the schema
		// is in place.
		EntityManager em = createEntityManager();
		// populate the schema.
		prepareDatabase("member");

		Member found = em.find(Member.class, UUID);

		assertNotNull(found);
		Member member = (Member) found;
		assertThat(member.getFirstName(), is(FIRST_NAME));
		assertThat(member.getInterests().size(),is(2));
		
		assertThat(member.getInterests().get(0).getContent(), is(INTEREST2));
		assertThat(member.getInterests().get(1).getContent(), is(INTEREST1));
	}

	@Test
	public void canAccessesMemberViaJpa() throws DatabaseUnitException,
			SQLException, IOException {

		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Member member = Fixture.createMember();
		member.setId(UUID2);
		em.persist(member);
		em.flush();
		em.getTransaction().commit();

		ITable actual = getCurrentDataset().getTable("member");
		ITable expected = loadDataset("member-post-write").getTable("member");
		new DbUnitAssert().assertEquals(expected, actual);
	}


	@After
	public void closeDatabase() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	private EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(TEST_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		return em;
	}

	/**
	 * Use config file to per-configure database.
	 * 
	 * @param baseName
	 *            name of xml file in classpath without .dbunit.xml suffix.
	 * @return IDataSet configured from the XML file.
	 * @throws IOException
	 * @throws DataSetException
	 *             if failed to load configuration file.
	 */
	protected void prepareDatabase(String config) throws SQLException,
			DatabaseUnitException, IOException {
		ensureConnected();
		IDataSet dataset = loadDataset(config);
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
	}

	/**
	 * Read the current database configuration.
	 * 
	 * @return IDataSet representing current state of database.
	 * @throws DatabaseUnitException
	 *             if failed to read current state of database.
	 * @throws SQLException
	 *             if failed to read current state of database.
	 */
	private IDataSet getCurrentDataset() throws DatabaseUnitException,
			SQLException {
		ensureConnected();
		return connection.createDataSet();
	}

	/**
	 * Ensure that a connection to the database has been established.
	 * 
	 * @throws DatabaseUnitException
	 *             if failed to create connection.
	 * @throws SQLException
	 *             if failed to create exception.
	 */
	private void ensureConnected() throws DatabaseUnitException, SQLException {
		if (connection == null) {
			connection = new DatabaseConnection(
					DriverManager.getConnection("jdbc:hsqldb:mem:test"));
		}
	}

	/**
	 * Load a FlatXmlDataSet file from the root of the classpath, assuming
	 * .dbunt.xml extension.
	 * 
	 * @param baseName
	 *            name of xml file in classpath without .dbunit.xml suffix.
	 * @return IDataSet configured from the XML file.
	 * @throws DataSetException
	 *             if failed to load configuration file.
	 * @throws IOException
	 */
	private IDataSet loadDataset(String baseName) throws DataSetException,
			IOException {
		String path = baseName + ".dbunit.xml";
		URL url = getClass().getClassLoader().getResource(path);
		return new XmlDataSet(url.openStream());
	}
}
