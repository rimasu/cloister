package org.rimasu.cloister.client.ui;

import java.util.List;

import org.rimasu.cloister.client.member.display.MemberDisplayPanel;
import org.rimasu.cloister.client.member.display.MemberDisplayPanel.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MemberDisplayPanelImpl extends Composite implements
		MemberDisplayPanel {

	private static MemberViewUiBinder uiBinder = GWT
			.create(MemberViewUiBinder.class);

	interface MemberViewUiBinder extends
			UiBinder<Widget, MemberDisplayPanelImpl> {
	}

	private Presenter presenter;
	
	@UiField
	Label firstNameLabel;
	
	@UiField
	Label surnameLabel;
	

	public MemberDisplayPanelImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setFirstName(String firstName) {
		firstNameLabel.setText(firstName);
	}

	@Override
	public void setSurname(String surname) {
		surnameLabel.setText(surname);
	}

	@Override
	public void setInterests(List<String> interests) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProjects(List<String> projects) {
		// TODO Auto-generated method stub
		
	}

}
