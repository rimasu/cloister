package org.rimasu.cloister.server.model.locator;

import java.util.ArrayList;
import java.util.List;

import org.rimasu.cloister.server.model.Fixture;
import org.rimasu.cloister.server.model.core.Member;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.requestfactory.shared.Locator;

public class MemberLocator extends AbstractEntityLocator<Member> {
	
	public MemberLocator()
	{
		super(Member.class);
	}

	@Override
	protected Member createEntity() {
		return new Member();
	}
	


}
