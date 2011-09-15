package org.rimasu.cloister.server.model.locator;

import org.rimasu.cloister.server.model.core.Message;

public class MessageService extends EntityService<Message> {
	private final PrincipalService principalService;

	public MessageService(PrincipalService principalService) {
		super(Message.class);
		this.principalService = principalService;
	}
}
