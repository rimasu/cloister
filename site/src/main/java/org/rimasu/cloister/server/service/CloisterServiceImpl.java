package org.rimasu.cloister.server.service;

import java.io.IOException;

import org.rimasu.cloister.client.support.CloisterService;
import org.rimasu.cloister.server.model.locator.PrincipalService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CloisterServiceImpl extends RemoteServiceServlet implements
		CloisterService {

	@Override
	public String testFunc() throws IOException {
		
		PrincipalService service = new PrincipalService();

		return service.getCurrent().getUsername();
	}

}
