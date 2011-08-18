package org.rimasu.cloister.server.backup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.rimasu.cloister.server.backup.Snapshot;
import org.rimasu.cloister.server.model.EntityTest;
import org.rimasu.cloister.server.model.Fixture;
import org.rimasu.cloister.server.model.auth.Principal;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.Message;

/**
 * Test Unit For Backup
 * 
 * @author richards
 * 
 */
public class SnapshotTest extends EntityTest {

	/**
	 * Name of persistence unit used during tests.
	 */
	private static final String TEST_PERSISTENCE_UNIT = "TEST_UNIT";

	/**
	 * List of tables that are exclude from simple DBUNIT comparison because
	 * they do not have a primary key.
	 */
	private static final Set<String> EXCLUDED_TABLES = new HashSet<String>(
			Arrays.asList("MEMBER_INTERESTS", "MEMBER_PROJECTS",
					"PRINCIPAL_ROLES"));

	/**
	 * Connection used by DBUNIT to access in memory database.
	 */
	private DatabaseConnection connection;

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
		assertThat(backUp.getPrincipals().size(), is(5));
		assertThat(backUp.getMessageBoxes().size(), is(10));
		assertThat(backUp.getMembers().size(), is(5));
		assertThat(backUp.getMessages().size(), is(9));

		Member member0 = backUp.getMembers().get(0);
		assertThat(member0.getFirstName(), is("Alfred"));
		assertThat(member0.getInbox(), is(backUp.getMessageBoxes().get(0)));
		assertThat(member0.getSentItems(), is(backUp.getMessageBoxes().get(1)));

		Member member1 = backUp.getMembers().get(1);
		assertThat(member1.getFirstName(), is("Ben"));
		assertThat(member1.getInbox(), is(backUp.getMessageBoxes().get(2)));
		assertThat(member1.getSentItems(), is(backUp.getMessageBoxes().get(3)));

		Member member2 = backUp.getMembers().get(2);
		assertThat(member2.getFirstName(), is("Chris"));
		assertThat(member2.getInbox(), is(backUp.getMessageBoxes().get(4)));
		assertThat(member2.getSentItems(), is(backUp.getMessageBoxes().get(5)));
	}

	@Test
	public void canExportXml() throws JAXBException, IOException {
		Snapshot backUp = Fixture.createSnapshot();
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage()
				.getName());

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter dest = new StringWriter();

		marshaller.marshal(backUp, dest);
		dest.close();
		String actual = dest.toString();
		System.out.println(actual);

		// TODO compare actual with expected.
	}

	@Test
	public void canValidateBackUp() {
		Snapshot backUp = Fixture.createSnapshot();

		assertValid(backUp);
		// make member first name invalid.
		backUp.getMembers().get(3).setFirstName("");
		assertInvalid(backUp, "members[3].firstName");
	}

	@Test
	public void canPopulateEntityManagerFromSnapshot() throws Exception {
		EntityManager manager = createEntityManager();
		Snapshot source = Fixture.createSnapshot();
		manager.getTransaction().begin();
		source.persistTo(manager);
		manager.flush();
		manager.getTransaction().commit();
		assertDataAsExpected("backup.dbunit.xml");
	}

	@Test
	public void canExtractSnapshotFromEntityManager() throws SQLException,
			DatabaseUnitException {
		EntityManager manager = createEntityManager();
		populateDataBase("backup.dbunit.xml");		
		Snapshot snapshot = Snapshot.create(manager);
		assertNotNull(snapshot.getCaptureDate());
		System.out.println(snapshot);
	}

	private void populateDataBase(String initialContents) throws SQLException,
			DatabaseUnitException {
		createFreshConnection();
		try {
			IDataSet template = loadDataSetFromClassPath(initialContents);
			DatabaseOperation.INSERT.execute(connection, template);
		} finally {
			disconnect();
		}
	}

	/**
	 * Assert that the database contains the expected content.
	 * 
	 * @param expectedDatabaseConfig
	 *            classpath location of XML file containing a complete
	 *            description of the expected database state.
	 * @throws SQLException
	 *             if failed to access database.
	 * @throws DatabaseUnitException
	 *             if failed to access database.
	 * @throws DataSetException
	 *             if failed to access data-set backup.
	 * @throws IOException
	 *             if failed to generate XML dump of actual database state.
	 */
	private void assertDataAsExpected(String expectedDatabaseConfig)
			throws SQLException, DatabaseUnitException, DataSetException,
			IOException {
		createFreshConnection();
		IDataSet actual = getCurrentDataset();
		IDataSet expected = loadDataSetFromClassPath(expectedDatabaseConfig);

		try {
			for (String tableName : expected.getTableNames()) {
				if (!EXCLUDED_TABLES.contains(tableName)) {
					Assertion.assertEquals(expected.getTable(tableName),
							actual.getTable(tableName));
				}
			}
		} catch (AssertionError e) {
			StringWriter writer = new StringWriter();
			writer.append("actual:");
			FlatXmlDataSet.write(actual, writer);
			writer.close();
			System.out.println(writer);
			throw e;
		}
	}

	private IDataSet loadDataSetFromClassPath(String path)
			throws DataSetException {
		FlatXmlDataSetBuilder bld = new FlatXmlDataSetBuilder();
		ClassLoader loader = getClass().getClassLoader();
		return bld.build(loader.getResource(path));
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
		return connection.createDataSet();
	}

	/**
	 * Create a new entity manager.
	 * 
	 * @return new entity manager.
	 */
	private EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(TEST_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		return em;
	}

	/**
	 * Create a new connection to database (will close any existing connection).
	 * 
	 * @throws SQLException
	 *             if failed to close or create connection.
	 * @throws DatabaseUnitException
	 *             if failed wrap connection for DBUNIT.
	 */
	private void createFreshConnection() throws SQLException,
			DatabaseUnitException {
		disconnect();
		connection = new DatabaseConnection(
				DriverManager.getConnection("jdbc:hsqldb:mem:test"));

	}

	/**
	 * Close the database connection (if any).
	 * 
	 * @throws SQLException
	 *             if failed to close connection.
	 */
	private void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

}
