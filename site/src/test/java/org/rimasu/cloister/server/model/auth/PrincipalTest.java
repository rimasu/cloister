package org.rimasu.cloister.server.model.auth;

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
		assertInvalid(principal, "use");
	}

}
