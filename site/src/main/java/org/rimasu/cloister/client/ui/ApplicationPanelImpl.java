package org.rimasu.cloister.client.ui;

import org.rimasu.cloister.client.site.ApplicationPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ApplicationPanelImpl extends Composite implements ApplicationPanel {

	private static ApplicationPanelImplUiBinder uiBinder = GWT
			.create(ApplicationPanelImplUiBinder.class);

	interface ApplicationPanelImplUiBinder extends
			UiBinder<Widget, ApplicationPanelImpl> {
	}
	
	@UiField
	SimplePanel entityPanel;

	public ApplicationPanelImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasOneWidget getEntityPanel() {
		return entityPanel;
	}

}
