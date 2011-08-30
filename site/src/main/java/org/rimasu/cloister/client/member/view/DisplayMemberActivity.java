package org.rimasu.cloister.client.member.view;

import org.rimasu.cloister.client.UiInjector;
import org.rimasu.cloister.client.support.MemberProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Display Member Activity.
 */
public class DisplayMemberActivity extends AbstractActivity implements
		MemberDisplayPanel.Presenter {
	
	private final UiInjector injector = GWT.create(UiInjector.class);


	/**
	 * Proxy providing access to the member being displayed.
	 */
	private MemberProxy member;


	private MemberDisplayPanel panel;

	public DisplayMemberActivity(MemberProxy member) {
		this.member = member;
	}

	@Override
	public void start(AcceptsOneWidget parent, EventBus eventBus) {
		panel = injector.getMemberDisplayPanel();
		panel.setPresenter(this);
		parent.setWidget(panel);
	}
}
