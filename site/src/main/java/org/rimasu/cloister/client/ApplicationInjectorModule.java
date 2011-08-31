package org.rimasu.cloister.client;

import org.rimasu.cloister.client.member.edit.EditMemberActivity;
import org.rimasu.cloister.client.member.edit.MemberEditPanel;
import org.rimasu.cloister.client.member.view.DisplayMemberActivity;
import org.rimasu.cloister.client.member.view.DisplayMemberPlace;
import org.rimasu.cloister.client.member.view.MemberDisplayPanel;
import org.rimasu.cloister.client.site.ApplicationPanel;
import org.rimasu.cloister.client.ui.ApplicationPanelImpl;
import org.rimasu.cloister.client.ui.MemberDisplayPanelImpl;
import org.rimasu.cloister.client.ui.MemberEditPanelImpl;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
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
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).to(PlaceControllerImpl.class).in(
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
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus,
				new DisplayMemberPlace(""));
		return historyHandler;
	}

	@Provides
	@Singleton
	public PlaceController getPlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}

	@Provides
	@Singleton
	public ActivityManager getActivityManager(ActivityMapper mapper,
			EventBus eventBus, HasOneWidget display) {
		ActivityManager activityManager = new ActivityManager(mapper, eventBus);
		activityManager.setDisplay(display);
		return activityManager;
	}

}
