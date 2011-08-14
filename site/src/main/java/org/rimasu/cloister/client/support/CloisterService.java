package org.rimasu.cloister.client.support;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface CloisterService extends RemoteService {
	public String testFunc();
}
