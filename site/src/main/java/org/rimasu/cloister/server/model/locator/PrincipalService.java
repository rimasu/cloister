package org.rimasu.cloister.server.model.locator;

import java.io.IOException;

import org.rimasu.cloister.server.model.auth.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PrincipalService extends EntityService<Principal>{	
	
	public PrincipalService(){
		super(Principal.class);
	}
	
	public Principal getCurrent() throws IOException
	{
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (authentication == null) {
			System.out.println("Not logged in");
			return null;
		} else {
			return  find((String)authentication.getPrincipal());
		}
	}
}
