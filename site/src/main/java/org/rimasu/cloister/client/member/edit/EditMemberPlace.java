package org.rimasu.cloister.client.member.edit;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditMemberPlace extends Place {

	private String id;

	public EditMemberPlace(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public String toString(){
		return "edit-member-place-" + id;
	}

	public static class Tokenizer implements PlaceTokenizer<EditMemberPlace> {
		@Override
		public String getToken(EditMemberPlace place) {
			return place.id;
		}

		@Override
		public EditMemberPlace getPlace(String token) {
			return new EditMemberPlace(token);
		}
	}
}
