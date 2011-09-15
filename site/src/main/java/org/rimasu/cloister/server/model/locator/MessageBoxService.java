package org.rimasu.cloister.server.model.locator;

import org.rimasu.cloister.server.model.core.MessageBox;

public class MessageBoxService extends EntityService<MessageBox>{
	
	private PrincipalService principalService;

	public MessageBoxService(PrincipalService principalService){
		super(MessageBox.class);
		this.principalService =principalService;
	}
}
