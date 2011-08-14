package org.rimasu.cloister.server.service;

import org.rimasu.cloister.client.support.Service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	@Override
	public String testFunc() {
		return "aoeu";
	}

}
