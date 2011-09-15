package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.locator.MemberService;
import org.rimasu.cloister.server.model.locator.MessageService;
import org.rimasu.cloister.server.model.locator.ServiceLocatorImpl;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = MessageService.class, locator = ServiceLocatorImpl.class)
public interface MessageRequest extends RequestContext {

	Request<List<MessageProxy>> findAll();

	Request<MessageProxy> find(String id);
}
