package org.rimasu.cloister.server.model.locator;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.rimasu.cloister.server.model.ModelLoader;
import org.rimasu.cloister.server.model.core.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.core.client.GWT;

public class EntityService<E> {

	private static final String FIND_ALL_FORMAT = "SELECT e FROM %s e";

	private final Class<E> type;

	protected EntityService(Class<E> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() throws IOException {
		EntityManager manager = ModelLoader.getContext();
		try {
			Query query = manager.createQuery(String.format(FIND_ALL_FORMAT,
					type.getSimpleName()));
			return (List<E>) query.getResultList();
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
	}

	public E find(String id) throws IOException {
		EntityManager manager = ModelLoader.getContext();
		try {
			return manager.find(type, id);
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
	}

}
