package org.rimasu.cloister.server.service;

import org.rimasu.cloister.client.support.CloisterService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CloisterServiceImpl extends RemoteServiceServlet implements
		CloisterService {

	@Override
	public String testFunc() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (authentication == null) {
			System.out.println("Not logged in");
			return null;
		} else {
			return (String) authentication.getPrincipal();
		}
	}

}
