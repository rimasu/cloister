package org.rimasu.cloister.client.support;

import org.rimasu.cloister.server.model.core.MessageBox;
import org.rimasu.cloister.server.model.locator.MessageBoxLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = MessageBox.class, locator = MessageBoxLocator.class)
public interface MessageBoxProxy extends EntityProxy {

}
