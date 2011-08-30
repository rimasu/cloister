package org.rimasu.cloister.client.ui;

import org.rimasu.cloister.client.member.edit.MemberEditPanel;
import org.rimasu.cloister.client.member.view.MemberDisplayPanel;

import com.google.gwt.inject.client.AbstractGinModule;

public class UiModuleImpl extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(ApplicationPanelImpl.class).to(ApplicationPanelImpl.class);
		bind(MemberDisplayPanel.class).to(MemberDisplayPanelImpl.class);
		bind(MemberEditPanel.class).to(MemberEditPanelImpl.class);
	}
}
