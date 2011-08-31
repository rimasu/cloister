package org.rimasu.cloister.client.member.view;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface MemberDisplayPanel extends IsWidget {
		
	public interface Presenter {
	
	}

	void setPresenter(Presenter displayMemberActivity);
		
	void setFirstName(String firstName);
	
	void setSurname(String surname);
	
	void setInterests(List<String> interests);
	
	void setProjects(List<String> projects);
	
	
}
