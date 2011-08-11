package org.rimasu.cloister.common.model;

import org.junit.Test;

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
