package org.rimasu.cloister.server.model;

import java.util.Calendar;
import java.util.UUID;

import org.rimasu.cloister.server.model.auth.Principal;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.MessageBox;
import org.rimasu.cloister.server.model.event.Callback;

public class Fixture {

	private static String[] firstNames = { "Alfred", "Ben", "Chris", "David",
			"Elizabeth", "Freddy", "Graham", "Henry", "Ian" };
	private static int nextFirstName = 0;

	public static Member createMember() {
		Member member = new Member(getUuid());

		MessageBox inbox = new MessageBox(getUuid(), "Inbox");
		MessageBox sentItems = new MessageBox(getUuid(), "Sent Items");

		member.setInbox(inbox);
		member.setSentItems(sentItems);
		member.getMessageBoxes().add(inbox);
		member.getMessageBoxes().add(sentItems);
		member.setFirstName(getFirstName());

		return member;
	}

	public static String getUuid() {
		return UUID.randomUUID().toString();
	}

	public static String getFirstName() {
		if (nextFirstName >= firstNames.length) {
			nextFirstName = 0;
		}
		return firstNames[nextFirstName++];
	}

	public static Principal createPrincipal() {
		Principal principal = new Principal();
		String firstName = getFirstName();
		principal.setUsername(firstName);
		principal.generatePasswordHash(firstName);
		return principal;
	}

	public static Callback createCallback() {
		Callback callback = new Callback();
		callback.setUuid(getUuid());
		callback.setSubject(createMember());
		callback.setExpiryDate(Calendar.getInstance());
		callback.setUsed(false);
		return callback;
	}

}
