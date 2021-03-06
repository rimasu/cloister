package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.locator.MemberService;
import org.rimasu.cloister.server.model.locator.ServiceLocatorImpl;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = MemberService.class, locator = ServiceLocatorImpl.class)
public interface MemberRequest extends RequestContext {

	Request<List<MemberProxy>> findAll();

	Request<MemberProxy> find(String memberId);
}
