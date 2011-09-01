package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.locator.MemberService;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(MemberService.class)
public interface MemberRequest extends RequestContext  {

	Request<List<MemberProxy>> findAll();

	Request<MemberProxy> findMember(String memberId);
	
}
