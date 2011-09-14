package org.rimasu.cloister.client;

import org.rimasu.cloister.client.member.edit.EditMemberActivity;
import org.rimasu.cloister.client.member.edit.EditMemberPlace;
import org.rimasu.cloister.client.member.view.DisplayMemberActivity;
import org.rimasu.cloister.client.member.view.DisplayMemberPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ApplicationActivityMapper implements ActivityMapper {

	private Provider<EditMemberActivity> editMemberActivityProvider;

	private Provider<DisplayMemberActivity> displayMemberActivityProvider;

	@Inject
	public ApplicationActivityMapper(
			Provider<EditMemberActivity> editMemberActivityProvider,
			Provider<DisplayMemberActivity> displayMemberActiviteProvider) {

		this.editMemberActivityProvider = editMemberActivityProvider;
		this.displayMemberActivityProvider = displayMemberActiviteProvider;
	}

	@Override
	public Activity getActivity(Place place) {
		GWT.log("Get activity for " + place);
		Activity result = null;
		if (place instanceof EditMemberPlace) {
			result = editMemberActivityProvider.get().withPlace(place);
		} else if(place instanceof DisplayMemberPlace){
			result = displayMemberActivityProvider.get().withPlace(place);
		}
		return result;
	}
}
