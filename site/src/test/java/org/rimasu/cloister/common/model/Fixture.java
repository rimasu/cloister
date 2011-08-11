package org.rimasu.cloister.common.model;

import java.util.UUID;

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

}
