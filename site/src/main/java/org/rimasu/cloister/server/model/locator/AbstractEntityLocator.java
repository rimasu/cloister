package org.rimasu.cloister.server.model.locator;

import org.rimasu.cloister.server.model.AbstractEntity;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.requestfactory.shared.Locator;

public abstract class AbstractEntityLocator<E extends AbstractEntity> extends Locator<E, String> {
	
	private final Class<E> domainType;

	public AbstractEntityLocator(Class<E> domainType)
	{
		super();
		GWT.log("Initialised locator for " + domainType.getSimpleName());
		this.domainType = domainType;
	}

	@Override
	public E create(Class<? extends E> clazz) {
		GWT.log("Creating entity");
		return createEntity();
	}

	protected abstract E createEntity();

	@Override
	public E find(Class<? extends E> clazz, String id) {
		GWT.log("Find entity " + id);
		return null;
	}

	@Override
	public String getId(E domainObject) {
		return domainObject.getId();
	}

	@Override
	public Object getVersion(E domainObject) {
		return domainObject.getVersion();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Class<E> getDomainType() {
		return domainType;
	}
}
