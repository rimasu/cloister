package org.rimasu.cloister.client;

import org.rimasu.cloister.client.member.edit.EditMemberActivity;
import org.rimasu.cloister.client.member.edit.MemberEditPanel;
import org.rimasu.cloister.client.member.view.DisplayMemberActivity;
import org.rimasu.cloister.client.member.view.DisplayMemberPlace;
import org.rimasu.cloister.client.member.view.MemberDisplayPanel;
import org.rimasu.cloister.client.site.ApplicationPanel;
import org.rimasu.cloister.client.support.CloisterRequestFactory;
import org.rimasu.cloister.client.ui.ApplicationPanelImpl;
import org.rimasu.cloister.client.ui.MemberDisplayPanelImpl;
import org.rimasu.cloister.client.ui.MemberEditPanelImpl;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class ApplicationInjectorModule extends AbstractGinModule {

	@Override
	protected void configure() {
		GWT.log("configuring injector module");
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

		bind(PlaceHistoryMapper.class).to(ApplicationPlaceHistoryMapper.class)
				.in(Singleton.class);

		bind(ActivityMapper.class).to(ApplicationActivityMapper.class).in(
				Singleton.class);

		bind(ApplicationPanel.class).to(ApplicationPanelImpl.class);

		bind(MemberDisplayPanel.class).to(MemberDisplayPanelImpl.class);

		bind(MemberEditPanel.class).to(MemberEditPanelImpl.class);

		bind(EditMemberActivity.class);

		bind(DisplayMemberActivity.class);
	}

	@Provides
	@Singleton
	public PlaceHistoryHandler getHistoryHandler(
			PlaceController placeController, PlaceHistoryMapper historyMapper,
			EventBus eventBus, ActivityManager activityManager) {		
		GWT.log("configuring history handler");
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus,
				new DisplayMemberPlace(""));

		return historyHandler;
	}

	@Provides
	@Singleton
	public CloisterRequestFactory getRequestFactory(EventBus eventBus) {		
		GWT.log("configuring request factory");		
		final CloisterRequestFactory requestFactory = GWT
				.create(CloisterRequestFactory.class);
		requestFactory.initialize(eventBus);
		return requestFactory;
	}

	@Provides
	@Singleton
	public PlaceController getPlaceController(EventBus eventBus) {
		GWT.log("configuring place controller");
		return new PlaceController(eventBus);
	}

	@Provides
	@Singleton
	public ActivityManager getActivityManager(ActivityMapper mapper,
			EventBus eventBus) {
		GWT.log("configuring activity manager");
		ActivityManager activityManager = new ActivityManager(mapper, eventBus);
		return activityManager;
	}

}
