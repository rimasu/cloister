package org.rimasu.cloister.client.support;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface CloisterRequestFactory extends RequestFactory {

	MemberRequest memberRequest();
	
	
}
