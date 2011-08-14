package org.rimasu.cloister.client;

import org.rimasu.cloister.client.support.Service;
import org.rimasu.cloister.client.support.ServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class EntryPointImpl implements EntryPoint {

	ServiceAsync service = (ServiceAsync) GWT.create(Service.class);

	@Override
	public void onModuleLoad() {
		
		service.testFunc(new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				GWT.log(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getLocalizedMessage());
			}
		});
		GWT.log("Cloister module started.");
	}
}
