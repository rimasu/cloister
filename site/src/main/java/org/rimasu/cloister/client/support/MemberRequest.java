package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.core.Member;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Member.class)
public interface MemberRequest extends RequestContext  {

	Request<List<MemberProxy>> findAll();
	
}
