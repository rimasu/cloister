package org.rimasu.cloister.client.member.edit;

import org.rimasu.cloister.client.support.MemberProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Display Member Activity.
 */
public class EditMemberActivity extends AbstractActivity {

	/**
	 * Proxy providing access to the member being displayed.
	 */
	private MemberProxy member;

	public EditMemberActivity(MemberProxy member) {
		this.member = member;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub

	}
}
