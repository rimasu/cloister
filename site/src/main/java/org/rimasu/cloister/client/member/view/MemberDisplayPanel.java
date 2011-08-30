package org.rimasu.cloister.client.member.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class MemberDisplayPanel extends Composite  {

	private static MemberViewUiBinder uiBinder = GWT
			.create(MemberViewUiBinder.class);

	interface MemberViewUiBinder extends UiBinder<Widget, MemberDisplayPanel> {
	}

	public MemberDisplayPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
