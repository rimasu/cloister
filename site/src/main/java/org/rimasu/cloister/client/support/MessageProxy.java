package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.core.Message;
import org.rimasu.cloister.server.model.locator.MessageLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Message.class, locator = MessageLocator.class)
public interface MessageProxy extends EntityProxy {

	String getTitle();

	String getContent();

	MemberProxy getSender();

	List<MemberProxy> getRecipients();

}
