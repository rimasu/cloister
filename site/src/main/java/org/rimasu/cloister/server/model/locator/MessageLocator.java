package org.rimasu.cloister.server.model.locator;

import org.rimasu.cloister.server.model.core.Message;

public class MessageLocator extends AbstractEntityLocator<Message> {
	public MessageLocator() {
		super(Message.class);
	}

	@Override
	protected Message createEntity() {
		return new Message();
	}
}
