package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.core.Member;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Member.class)
public interface MemberProxy extends EntityProxy {

	String getFirstName();
	
	MessageBoxProxy getInbox();
	
	MessageBoxProxy getSentItems();
	
	List<MessageBoxProxy> getMessageBoxes();
	
	List<BlockTextProxy> getInterests();
	
	List<BlockTextProxy> getProjects();
	
}
