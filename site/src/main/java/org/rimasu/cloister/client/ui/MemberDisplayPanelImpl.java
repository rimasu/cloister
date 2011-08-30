package org.rimasu.cloister.client.ui;

import org.rimasu.cloister.client.member.view.MemberDisplayPanel;
import org.rimasu.cloister.client.member.view.MemberDisplayPanel.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MemberDisplayPanelImpl extends Composite implements
		MemberDisplayPanel {

	private static MemberViewUiBinder uiBinder = GWT
			.create(MemberViewUiBinder.class);

	interface MemberViewUiBinder extends
			UiBinder<Widget, MemberDisplayPanelImpl> {
	}

	private Presenter presenter;

	public MemberDisplayPanelImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
