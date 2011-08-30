package org.rimasu.cloister.client.member.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Place that edits a members details.
 * 
 * @author richards
 * 
 */
public class DisplayMemberPlace extends Place {

	private String id;

	public DisplayMemberPlace(String id) {
		this.id = id;
	}

	public static class Tokenizer implements PlaceTokenizer<DisplayMemberPlace> {
		@Override
		public String getToken(DisplayMemberPlace place) {
			return place.id;
		}

		@Override
		public DisplayMemberPlace getPlace(String token) {
			return new DisplayMemberPlace(token);
		}
	}
}