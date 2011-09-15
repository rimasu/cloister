package org.rimasu.cloister.server.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AbstractEntityTest extends EntityTest {

	private static final String UUID1 = "51d04e30-bdd3-11e0-962b-0800200c9a66";
	private static final String UUID2 = "51d04e30-bdd3-11e9-962b-0800200c9a66";

	@Test
	public void toStringIncludesClassNameAndUuid() {
		ConcreteEntity entity = new ConcreteEntity(UUID1);
		assertThat(entity.toString(),
				is("ConcreteEntity#51d04e30-bdd3-11e0-962b-0800200c9a66"));

	}

	@Test
	public void canChangeAMembersUuid() {
		ConcreteEntity entity = new ConcreteEntity(UUID1);
		assertThat(entity.getId(), is(UUID1));
	}

	@Test
	public void nullUuidIsIsReportedByValidationReport() {
		ConcreteEntity entity = new ConcreteEntity(null);
		assertInvalid(entity, "id");
	}

	@Test
	public void notValidUuidIsReportedByValidationReport() {
		ConcreteEntity entity = new ConcreteEntity("0237eee0-bNOTV-ALID-0800200c9a666");		
		assertInvalid(entity, "id");
	}

	@Test
	public void equalsAndHashCodeFollowUuid() {
		ConcreteEntity entity1a = new ConcreteEntity(UUID1);
		ConcreteEntity entity1b = new ConcreteEntity(UUID1);
		
		ConcreteEntity entity2a = new ConcreteEntity(UUID2);
		ConcreteEntity entity2b = new ConcreteEntity(UUID2);
		
		ConcreteEntity entityNull1 = new ConcreteEntity(null);
		ConcreteEntity entityNull2 = new ConcreteEntity(null);
		
		assertTrue(entity1a.equals(entity1a));
		assertTrue(entity1a.equals(entity1b));
		assertTrue(entity1b.equals(entity1a));
		assertTrue(entity1b.equals(entity1b));
		
		assertTrue(entity2a.equals(entity2a));
		assertTrue(entity2a.equals(entity2b));
		assertTrue(entity2b.equals(entity2a));
		assertTrue(entity2b.equals(entity2b));
		
		assertFalse(entity1a.equals(entity2a));		
		assertFalse(entity2a.equals(entity1a));
		
		assertThat(entity1a.hashCode(), is(entity1b.hashCode()));
		assertThat(entity2a.hashCode(), is(entity2b.hashCode()));
		
		assertFalse(entity1a.hashCode() == entity2a.hashCode());	
		
		// having a null id is invalid, but should check
		// basic operations do not fail.
		assertFalse(entityNull1.equals(entity1a));	
		assertFalse(entityNull1.equals(entity2a));
		assertFalse(entity1a.equals(entityNull1));
		assertFalse(entity2a.equals(entityNull1));
		
		// need to check that this is correct
		assertTrue(entityNull1.equals(entityNull2));
		assertTrue((entityNull1.hashCode() == entityNull2.hashCode()));		
	}

	private class ConcreteEntity extends AbstractEntity {
		public ConcreteEntity(String uuid) {
			super(uuid);
		}
	}

}
