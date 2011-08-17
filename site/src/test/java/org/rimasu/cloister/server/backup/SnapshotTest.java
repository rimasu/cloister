package org.rimasu.cloister.server.backup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
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

	private static final String TEST_PERSISTENCE_UNIT = "TEST_UNIT";

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

		// Message message0 = backUp.getMessages().get(0);
		// assertThat(message0.getId(),
		// is("0161c3c0-c288-11e0-962b-0800200c9a66"));
		// assertThat(message0.getTitle(), is("Title content"));
		// assertThat(message0.getContent(), is("Content content"));
		// assertThat(message0.getSender(), is(member0));
		// assertThat(message0.getRecipients().size(), is(2));
		// assertThat(message0.getRecipients().get(0), is(member1));
		// assertThat(message0.getRecipients().get(1), is(member2));
		// assertThat(message0.getStatus(), is(Message.Status.READ));
		// assertThat(message0.getSendDate());
	}

	@Test
	public void canExportXml() throws JAXBException {
		Snapshot backUp = Fixture.createSnapshot();
		JAXBContext context = JAXBContext.newInstance(getClass().getPackage()
				.getName());

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter dest = new StringWriter();

		marshaller.marshal(backUp, dest);
		System.out.println(dest);

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
	public void canCreateSnapshotFromEntityManager() throws JAXBException,
			SQLException, DatabaseUnitException, IOException {


		EntityManager manager = createEntityManager();
		
		Snapshot source = Fixture.createSnapshot();
		
		manager.getTransaction().begin();
		source.persistTo(manager);		
		manager.flush();
		manager.getTransaction().commit();
		

		createFreshConnection();

		//Snapshot result = Snapshot.create(manager);

		IDataSet set = getCurrentDataset();
		StringWriter dest = new StringWriter();
		
		FlatXmlDataSet.write(set, dest);
		
		System.out.println(dest.toString());

		
//
//		JAXBContext context = JAXBContext.newInstance(getClass().getPackage()
//				.getName());
//
//		Marshaller marshaller = context.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		StringWriter dest = new StringWriter();
//
//		marshaller.marshal(result, dest);
//		System.out.println(dest);

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

	private EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(TEST_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		return em;
	}

	private void createFreshConnection() throws SQLException, DatabaseUnitException {
		disconnect();
		connection = new DatabaseConnection(
				DriverManager.getConnection("jdbc:hsqldb:mem:test"));

	}

	private void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

}
