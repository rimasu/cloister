package org.rimasu.cloister.server.model.locator;

import org.rimasu.cloister.server.model.core.MessageBox;

public class MessageBoxLocator extends AbstractEntityLocator<MessageBox> {
	public MessageBoxLocator() {
		super(MessageBox.class);
	}

	@Override
	protected MessageBox createEntity() {
		return new MessageBox();
	}
}
