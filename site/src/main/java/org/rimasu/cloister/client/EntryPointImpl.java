package org.rimasu.cloister.client;

import java.util.List;

import org.rimasu.cloister.client.site.ApplicationPanel;
import org.rimasu.cloister.client.support.CloisterRequestFactory;
import org.rimasu.cloister.client.support.CloisterService;
import org.rimasu.cloister.client.support.CloisterServiceAsync;
import org.rimasu.cloister.client.support.MemberProxy;
import org.rimasu.cloister.client.support.MemberRequest;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;

public class EntryPointImpl implements EntryPoint {
	
	private final UiInjector injector = GWT.create(UiInjector.class);
	
	CloisterServiceAsync service = (CloisterServiceAsync) GWT
			.create(CloisterService.class);

	private ApplicationPanel applicationPanel;

	@Override
	public void onModuleLoad() {

		testPingService();

		final EventBus eventBus = new SimpleEventBus();
		CloisterRequestFactory requestFactory = (CloisterRequestFactory) GWT
				.create(CloisterRequestFactory.class);
		requestFactory.initialize(eventBus);
		GWT.log("Request Factory Initialized");
		testPingRequestFactory(requestFactory);

		GWT.log("Cloister module started.");
		
		applicationPanel = injector.getApplicationPanel();		
		RootLayoutPanel.get().add(applicationPanel);
		
	}

	private void testPingRequestFactory(CloisterRequestFactory requestFactory) {
		MemberRequest request = requestFactory.memberRequest();
		
		request.findAll().fire(new Receiver<List<MemberProxy>>() {
			@Override
			public void onSuccess(List<MemberProxy> response) {
				GWT.log("Member Count" + response.size());	
				for(MemberProxy proxy : response)
				{
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
