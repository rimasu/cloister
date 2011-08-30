package org.rimasu.cloister.client.ui;

import org.rimasu.cloister.client.member.edit.MemberEditPanel;
import org.rimasu.cloister.client.member.edit.MemberEditPanel.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MemberEditPanelImpl extends Composite implements MemberEditPanel {

	private static MemberEditPanelUiBinder uiBinder = GWT
			.create(MemberEditPanelUiBinder.class);

	interface MemberEditPanelUiBinder extends
			UiBinder<Widget, MemberEditPanelImpl> {
	}

	private Presenter presenter;

	public MemberEditPanelImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
}
