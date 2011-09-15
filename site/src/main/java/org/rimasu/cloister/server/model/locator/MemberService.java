package org.rimasu.cloister.server.model.locator;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.rimasu.cloister.server.model.ModelLoader;
import org.rimasu.cloister.server.model.core.Member;

public class MemberService extends EntityService<Member> {

	private final PrincipalService prinicpalService;

	public MemberService(PrincipalService principalService) {
		super(Member.class);
		this.prinicpalService = principalService;
	}

}
