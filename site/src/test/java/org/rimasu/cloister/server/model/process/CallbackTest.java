package org.rimasu.cloister.server.model.process;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.rimasu.cloister.server.model.EntityTest;
import org.rimasu.cloister.server.model.Fixture;
import org.rimasu.cloister.server.model.process.Callback;

/**
 * Test unit for callback.
 */
public class CallbackTest extends EntityTest {

	@Test
	public void canValidCallback() {
		Callback callback = Fixture.createCallback();
		assertValid(callback);
	}

	@Test
	public void nullTypeIsInvalid() {
		Callback callback = Fixture.createCallback();
		callback.setType(null);
		assertInvalid(callback, "type");
	}

	@Test
	public void nullSubjectIsInvalid() {
		Callback callback = Fixture.createCallback();
		callback.setSubject(null);
		assertInvalid(callback, "subject");
	}

	@Test
	public void nullExpiryDateIsInvalid() {
		Callback callback = Fixture.createCallback();
		callback.setExpiryDate(null);
		assertInvalid(callback, "expiryDate");
	}

	@Test
	public void canTestExpiry() {
		Callback callback = Fixture.createCallback();
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MINUTE, -10);
		callback.setExpiryDate(date);
		assertTrue(callback.hasExpired());
		date.add(Calendar.MINUTE,20);
		callback.setExpiryDate(date);
		assertFalse(callback.hasExpired());
	}
}
