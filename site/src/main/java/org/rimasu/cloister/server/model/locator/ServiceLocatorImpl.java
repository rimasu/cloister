package org.rimasu.cloister.server.model.locator;

import java.util.Map;

import sun.security.jca.GetInstance;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class ServiceLocatorImpl implements ServiceLocator {

	private static Map<String, Object> lookup;

	static {
		lookup = new HashMap<String, Object>();
		PrincipalService principalService = new PrincipalService();
		register(principalService);
		register(new MemberService(principalService));
		register(new MessageService(principalService));
		register(new MessageBoxService(principalService));
		register(new CallbackService(principalService));
	}

	private static void register(Object object) {
		String key = object.getClass().getCanonicalName();
		lookup.put(key, object);
	}

	@Override
	public Object getInstance(Class<?> service) {
		return lookup.get(service.getCanonicalName());
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> type) {
		return (T) lookup.get(type.getCanonicalName());
	}

}
