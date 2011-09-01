package org.rimasu.cloister.server.model.locator;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import org.rimasu.cloister.server.model.ModelLoader;
import org.rimasu.cloister.server.model.core.Member;

import com.google.gwt.core.client.GWT;

public class MemberService {

	public static List<Member> findAll() throws IOException {
		GWT.log("Find all members.");
		EntityManager manager = null;
		manager = ModelLoader.getContext();
		try {
			return Member.findAllInternal(manager);
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
	}

	public static Member findMember(String memberId) throws IOException {
		GWT.log("Find member");
		EntityManager manager = null;
		manager = ModelLoader.getContext();
		try {
			return manager.find(Member.class, memberId);
		} finally {
			if (manager != null) {
				manager.close();
			}
		}

	}
}
