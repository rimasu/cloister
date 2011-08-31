package org.rimasu.cloister.client.member.view;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Display Member Activity.
 */
public class DisplayMemberActivity extends AbstractActivity implements
		MemberDisplayPanel.Presenter {

	private MemberDisplayPanel panel;
	private String memberId;

	@Inject
	public DisplayMemberActivity(MemberDisplayPanel panel) {
		this.panel = panel;
	}
	
	public Activity withPlace(Place place) {
		this.memberId = ((DisplayMemberPlace)place).getId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget parent, EventBus eventBus) {		
		panel.setPresenter(this);
		parent.setWidget(panel);
	}
}
