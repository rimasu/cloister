package org.rimasu.cloister.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class PlaceControllerImpl  extends PlaceController {

	@Inject
	public PlaceControllerImpl(EventBus eventBus) {
		super(eventBus);
	}
	
	
	@Override
	public void goTo(Place place)
	{
		GWT.log(place.toString());
		super.goTo(place);
	}

}
