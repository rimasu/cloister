package org.rimasu.cloister.server.model.locator;

import org.rimasu.cloister.server.model.process.Callback;

public class CallbackService extends EntityService<Callback> {
	private final PrincipalService prinicpalService;
	public CallbackService(PrincipalService principalService) {
		super(Callback.class);
		this.prinicpalService = principalService;
	}
}
