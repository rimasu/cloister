package org.rimasu.cloister.server.model.locator;

import org.rimasu.cloister.server.model.core.Member;

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
