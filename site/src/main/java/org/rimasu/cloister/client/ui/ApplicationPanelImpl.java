package org.rimasu.cloister.client.ui;

import org.rimasu.cloister.client.site.ApplicationPanel;

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

public class ApplicationPanelImpl extends Composite implements ApplicationPanel {

	private static ApplicationPanelImplUiBinder uiBinder = GWT
			.create(ApplicationPanelImplUiBinder.class);

	interface ApplicationPanelImplUiBinder extends
			UiBinder<Widget, ApplicationPanelImpl> {
	}

	public ApplicationPanelImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
