package org.rimasu.cloister.client;

import java.util.List;

import org.rimasu.cloister.client.member.display.DisplayMemberPlace;
import org.rimasu.cloister.client.site.ApplicationPanel;
import org.rimasu.cloister.client.support.CloisterRequestFactory;
import org.rimasu.cloister.client.support.CloisterService;
import org.rimasu.cloister.client.support.CloisterServiceAsync;
import org.rimasu.cloister.client.support.MemberProxy;
import org.rimasu.cloister.client.support.MemberRequest;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class Application {

	CloisterServiceAsync service = (CloisterServiceAsync) GWT
			.create(CloisterService.class);

	private final ApplicationPanel panel;

	private final EventBus eventBus;

	private final PlaceController placeController;

	private final ActivityManager activityManager;
	
	private final PlaceHistoryHandler historyHandler;

	@Inject
	public Application(
			ApplicationPanel panel,
			EventBus eventBus,
			PlaceController placeController,
			ActivityManager activityManager,
			PlaceHistoryHandler historyHandler
			
			) {
		super();
		this.panel = panel;
		this.eventBus = eventBus;
		this.placeController = placeController;			
		this.activityManager = activityManager;
		this.historyHandler = historyHandler;
	}

	public void start() {
		testPingService();

		CloisterRequestFactory requestFactory = (CloisterRequestFactory) GWT
				.create(CloisterRequestFactory.class);

		requestFactory.initialize(eventBus);

		GWT.log("Request Factory Initialized");
		testPingRequestFactory(requestFactory);

		GWT.log("Cloister module started.");

		
		

        // Start ActivityManager for the main widget with our ActivityMapper
        activityManager.setDisplay(panel.getEntityPanel());

		
        
        RootLayoutPanel.get().add(panel);
				
        historyHandler.handleCurrentHistory();
        
		placeController.goTo(new DisplayMemberPlace("d741a462-9b58-460d-b6e7-187276a86d26"));

	}

	private void testPingRequestFactory(CloisterRequestFactory requestFactory) {
		MemberRequest request = requestFactory.memberRequest();

		request.findAll().fire(new Receiver<List<MemberProxy>>() {
			@Override
			public void onSuccess(List<MemberProxy> response) {
				GWT.log("Member Count" + response.size());
				for (MemberProxy proxy : response) {
					GWT.log(proxy.getFirstName());
					GWT.log(proxy.getId());
				}
			}
		});
	}

	private void testPingService() {
		service.testFunc(new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				GWT.log(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getLocalizedMessage());
			}
		});
	}

}
