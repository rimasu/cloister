package org.rimasu.cloister.client.support;

import java.util.List;

import org.rimasu.cloister.server.model.core.Message;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Message.class)
public interface MessageProxy extends EntityProxy {
	
	String getTitle();
	
	String getContent();
	
	MemberProxy getSender();
	
	List<MemberProxy> getRecipients();
	
}
