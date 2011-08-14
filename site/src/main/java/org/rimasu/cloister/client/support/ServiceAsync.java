package org.rimasu.cloister.client.support;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceAsync {

	void testFunc(AsyncCallback<String> callback);

}
