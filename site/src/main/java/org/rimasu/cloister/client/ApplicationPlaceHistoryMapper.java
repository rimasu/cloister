package org.rimasu.cloister.client;

import org.rimasu.cloister.client.member.display.DisplayMemberPlace;
import org.rimasu.cloister.client.member.edit.EditMemberPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ EditMemberPlace.Tokenizer.class,
		DisplayMemberPlace.Tokenizer.class })
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {

}
