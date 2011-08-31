package org.rimasu.cloister.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class EntryPointImpl implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		
		ApplicationInjector injector = GWT.create(ApplicationInjector.class);
		
		Application application = injector.getApplication();
		
		application.start();
				
	}
}
