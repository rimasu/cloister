package org.rimasu.cloister.server.model.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.rimasu.cloister.server.model.EntityTest;
import org.rimasu.cloister.server.model.Fixture;
import org.rimasu.cloister.server.model.auth.Principal;

/**
 * Test unit for {@link Principal}.
 * @author rich
 *
 */
public class PrincipalTest extends EntityTest {
	
	@Test
	public void isInvalidIfUserNameIsNull()
	{
		Principal principal = Fixture.createPrincipal();
		principal.setUsername(null);
		assertInvalid(principal, "username");
	}
	
	@Test
	public void isInvalidIfUserNameIsShort()
	{
		Principal principal = Fixture.createPrincipal();
		principal.setUsername(null);
		assertInvalid(principal, "username");
	}
	
	@Test
	public void generatesDifferentHashes()
	{
		String examplePassword ="45aoeunth341!";
		Principal one = new Principal();
		Principal two = new Principal();
		one.setPasswordHashFromPassword(examplePassword);
		two.setPasswordHashFromPassword(examplePassword);
		
		// check that hashes are different.
		assertFalse(one.getPasswordHash().equals(two.getPasswordHash()));	
		
		// but that behaviour is the same.
		String wrongPassword = "45aoeunth341";		
		assertTrue(one.checkPassword(examplePassword));
		assertTrue(two.checkPassword(examplePassword));
		assertFalse(one.checkPassword(wrongPassword));
		assertFalse(two.checkPassword(wrongPassword));		
	}

}
