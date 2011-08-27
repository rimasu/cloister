package org.rimasu.cloister.server.secure;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.rimasu.cloister.server.model.ModelLoader;
import org.rimasu.cloister.server.model.auth.Principal;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationProviderImpl implements AuthenticationProvider {

	private static Map<String, String> users = new HashMap<String, String>();

	static {
		users.put("fabrizio", "javacodegeeks");
		users.put("justin", "javacodegeeks");
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		EntityManager manager = null;
		try {
			try {
				manager = ModelLoader.getContext();
			} catch (IOException e) {
				// possibly should add some king of authentication
				// exception here (it also extends runtime).
				throw new RuntimeException(e);
			}

			Principal principal = Principal.find(manager, username);

			if (principal == null) {
				throw new UsernameNotFoundException("User not found");
			}

			if (principal.checkPassword(password)) {
				Authentication customAuthentication = new AuthenticationImpl(
						"ROLE_USER", authentication);
				customAuthentication.setAuthenticated(true);

				return customAuthentication;

			} else {
				throw new BadCredentialsException("Invalid password");
			}

		} finally {
			if (manager != null) {
				manager.close();
			}
		}

	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
	}
}