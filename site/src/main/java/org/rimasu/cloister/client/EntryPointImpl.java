package org.rimasu.cloister.client;

import java.util.List;

import org.rimasu.cloister.client.support.CloisterRequestFactory;
import org.rimasu.cloister.client.support.CloisterService;
import org.rimasu.cloister.client.support.CloisterServiceAsync;
import org.rimasu.cloister.client.support.MemberProxy;
import org.rimasu.cloister.client.support.MemberRequest;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class EntryPointImpl implements EntryPoint {

	CloisterServiceAsync service = (CloisterServiceAsync) GWT
			.create(CloisterService.class);

	@Override
	public void onModuleLoad() {

		testPingService();

		final EventBus eventBus = new SimpleEventBus();
		CloisterRequestFactory requestFactory = (CloisterRequestFactory) GWT
				.create(CloisterRequestFactory.class);
		requestFactory.initialize(eventBus);

		testPingRequestFactory(requestFactory);

		GWT.log("Cloister module started.");
	}

	private void testPingRequestFactory(CloisterRequestFactory requestFactory) {
		MemberRequest request = requestFactory.memberRequest();
		request.findAll().fire(new Receiver<List<MemberProxy>>() {
			@Override
			public void onSuccess(List<MemberProxy> response) {
				for (MemberProxy proxy : response) {
					GWT.log(proxy.getFirstName());
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
