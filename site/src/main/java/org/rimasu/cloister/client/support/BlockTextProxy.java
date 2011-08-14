package org.rimasu.cloister.client.support;

import org.rimasu.cloister.server.model.core.BlockText;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(BlockText.class)
public interface BlockTextProxy extends ValueProxy {
	String getContent();
}
