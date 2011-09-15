package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.locator.MessageBoxService;
import org.rimasu.cloister.server.model.locator.ServiceLocatorImpl;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = MessageBoxService.class, locator = ServiceLocatorImpl.class)
public interface MessageBoxRequest extends RequestContext {

	Request<List<MessageBoxProxy>> findAll();

	Request<MessageBoxProxy> find(String id);
}
