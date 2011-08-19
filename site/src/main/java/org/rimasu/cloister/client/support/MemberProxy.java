package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.locator.MemberLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Member.class, locator = MemberLocator.class)
public interface MemberProxy extends EntityProxy {
	
	String getId();
	
	Integer getVersion();	

	String getFirstName();

	MessageBoxProxy getInbox();

	MessageBoxProxy getSentItems();

	List<MessageBoxProxy> getMessageBoxes();

	List<BlockTextProxy> getInterests();

	List<BlockTextProxy> getProjects();

}
