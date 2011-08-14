package org.rimasu.cloister.server.service;

import org.rimasu.cloister.client.support.CloisterService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CloisterServiceImpl extends RemoteServiceServlet implements CloisterService {

	@Override
	public String testFunc() {
		return "aoeu";
	}

}
