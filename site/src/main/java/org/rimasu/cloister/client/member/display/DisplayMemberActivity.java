package org.rimasu.cloister.client.member.display;

import org.rimasu.cloister.client.ProxyReciever;
import org.rimasu.cloister.client.ProxyTarget;
import org.rimasu.cloister.client.support.CloisterRequestFactory;
import org.rimasu.cloister.client.support.MemberProxy;
import org.rimasu.cloister.client.support.MemberRequest;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Request;

/**
 * Display Member Activity.
 */
public class DisplayMemberActivity extends AbstractActivity implements
		MemberDisplayPanel.Presenter, ProxyTarget<MemberProxy> {

	/**
	 * Panel used to display member.
	 */
	private final MemberDisplayPanel panel;

	/**
	 * Request factory used to access member details.
	 */
	private final CloisterRequestFactory requestFactory;

	/**
	 * The id of the member being displayed.
	 */
	private String memberId;

	@Inject
	public DisplayMemberActivity(MemberDisplayPanel panel,
			CloisterRequestFactory requestFactory) {
		this.panel = panel;
		this.requestFactory = requestFactory;
	}

	public Activity withPlace(Place place) {
		this.memberId = ((DisplayMemberPlace) place).getId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget parent, EventBus eventBus) {
		GWT.log("Display member activity started" + memberId);
		panel.setPresenter(this);
		parent.setWidget(panel);
		MemberRequest request = requestFactory.memberRequest();
		Request<MemberProxy> findRequest = request.find(memberId);
		findRequest.fire(new ProxyReciever<MemberProxy>(this));
	}

	@Override
	public void setProxy(MemberProxy proxy) {
		panel.setFirstName(proxy.getFirstName());
		panel.setSurname(proxy.getSurname());
	}

}
