package org.rimasu.cloister.server.model;

import java.io.IOException;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ModelLoader {

	private static final String PERSITENCE_UNIT_NAME_KEY = "jpa.unit";

	private static final String DEFAULT_UNIT_NAME = "PRODUCTION_UNIT";

	private static final String SNAPSHOT_PATH_KEY = "snapshot.path";

	private static ModelLoader singleton;

	private EntityManagerFactory factory;

	/**
	 * Private Constructor.
	 * 
	 * @throws IOException
	 *             if tried, but failed, to load a snapshot.
	 * 
	 */
	private ModelLoader() throws IOException {
		String unitName = System.getProperty(PERSITENCE_UNIT_NAME_KEY,
				DEFAULT_UNIT_NAME);
		factory = Persistence.createEntityManagerFactory(unitName);

		String snapshotPath = System.getProperty(SNAPSHOT_PATH_KEY);

		if (snapshotPath != null) {
			loadSnapshot(snapshotPath);
		}
	}

	private void loadSnapshot(String snapshotPath) throws IOException {
		URL url = getClass().getClassLoader().getResource(snapshotPath);
		if (url == null) {
			throw new IOException("Could not find snapshot with path "
					+ snapshotPath + " on class path.");
		}

		Snapshot snapshot = Snapshot.load(url);
		EntityManager context = null;
		try {
			context = factory.createEntityManager();
			context.getTransaction().begin();
			snapshot.persistTo(context);
			context.getTransaction().commit();
		} finally {
			if (context != null) {
				if (context.getTransaction().isActive()) {
					context.getTransaction().rollback();
				}
				context.close();
			}
		}
	}

	public static EntityManager getContext() throws IOException {
		ModelLoader loader = getInstance();
		return loader.createEntityManager();
	}

	private EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

	private static ModelLoader getInstance() throws IOException {
		if (singleton == null) {
			singleton = new ModelLoader();
		}
		return singleton;
	}

}
