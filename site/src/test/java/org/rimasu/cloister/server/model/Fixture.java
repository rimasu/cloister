package org.rimasu.cloister.server.model;

import java.util.Calendar;
import java.util.UUID;

import org.rimasu.cloister.server.backup.Snapshot;
import org.rimasu.cloister.server.model.auth.Principal;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.MessageBox;
import org.rimasu.cloister.server.model.event.Callback;
import org.rimasu.cloister.server.model.event.Callback.Type;

public class Fixture {

	private static String[] firstNames = { "Alfred", "Ben", "Chris", "David",
			"Elizabeth", "Freddy", "Graham", "Henry", "Ian" };

	private static String[] surnames = { "Adams", "Benforth", "Carter",
			"Douglas", "Edwards", "Fisher", "Green" };
	private static int nextFirstName = 0;
	private static int nextSurname = 0;

	public static void reset() {
		UuidTable.reset();
		nextFirstName = 0;
		nextSurname = 0;
	}

	public static Member createMember() {
		Member member = new Member(getUuid());

		MessageBox inbox = new MessageBox(getUuid(), "Inbox");
		MessageBox sentItems = new MessageBox(getUuid(), "Sent Items");

		member.setInbox(inbox);
		member.setSentItems(sentItems);
		member.getMessageBoxes().add(inbox);
		member.getMessageBoxes().add(sentItems);
		member.setFirstName(getFirstName());
		member.setSurname(getSurname());
		member.setPrincipal(createPrinicpal(member));
		return member;
	}

	public static String getUserName(Member member) {
		return member.getFirstName() + member.getSurname();
	}

	public static String getPassword(Member member) {
		return getUserName(member) + "1";
	}

	public static String getUuid() {
		return UuidTable.getUuid();
	}

	public static String getFirstName() {
		if (nextFirstName >= firstNames.length) {
			nextFirstName = 0;
		}
		return firstNames[nextFirstName++];
	}

	public static String getSurname() {
		if (nextSurname >= surnames.length) {
			nextSurname = 0;
		}
		return surnames[nextSurname++];
	}

	public static Principal createPrinicpal(Member member) {
		String username = getUserName(member);
		String password = getPassword(member);
		Principal principal = createPrincipal(username, password);
		return principal;
	}

	public static Principal createPrincipal(String username, String password) {
		Principal principal = new Principal();
		principal.setUsername(username);
		principal.generatePasswordHash(password);
		return principal;
	}

	public static Principal createPrincipal() {
		String firstName = getFirstName();
		return createPrincipal(firstName, firstName);

	}

	public static Callback createCallback() {
		Callback callback = new Callback();
		callback.setType(Type.EMAIL_CONFIRMATION);
		callback.setId(getUuid());
		callback.setSubject(createMember());
		callback.setExpiryDate(Calendar.getInstance());
		callback.setUsed(false);
		return callback;
	}

	public static Snapshot createSnapshot() {
		Snapshot result = new Snapshot();

		for (int i = 0; i < 4; i++) {
			Member member = createMember();
			result.getPrincipals().add(member.getPrincipal());
			result.getMessageBoxes().addAll(member.getMessageBoxes());
			result.getMembers().add(member);
		}

		return result;
	}

}
