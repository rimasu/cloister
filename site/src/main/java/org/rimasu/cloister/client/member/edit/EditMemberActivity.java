package org.rimasu.cloister.client.member.edit;

import org.rimasu.cloister.client.UiInjector;
import org.rimasu.cloister.client.support.MemberProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Display Member Activity.
 */
public class EditMemberActivity extends AbstractActivity implements
		MemberEditPanel.Presenter {
	
	private final UiInjector injector = GWT.create(UiInjector.class);

	
	/**
	 * Proxy providing access to the member being displayed.
	 */
	private MemberProxy member;


	private MemberEditPanel panel;

	public EditMemberActivity(MemberProxy member) {
		this.member = member;
	}

	@Override
	public void start(AcceptsOneWidget parent, EventBus eventBus) {
		panel = injector.getMemberEditPanel();
		panel.setPresenter(this);
		parent.setWidget(panel);
	}
}
