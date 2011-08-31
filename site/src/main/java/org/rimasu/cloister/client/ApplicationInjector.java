package org.rimasu.cloister.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;


@GinModules(value = {ApplicationInjectorModule.class})
public interface ApplicationInjector extends Ginjector {	
	
	Application getApplication();	
}
