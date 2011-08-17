package org.rimasu.cloister.server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.rimasu.cloister.server.backup.Snapshot;
import org.rimasu.cloister.server.model.auth.Principal;
import org.rimasu.cloister.server.model.auth.Role;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.Message;
import org.rimasu.cloister.server.model.core.Message.Status;
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
		principal.setPasswordHash(PasswordHashTable.getPasswordHash(password));
		principal.getRoles().add(Role.MEMBER);
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

		int memberCount = 5;
		for (int i = 0; i < memberCount; i++) {
			Member member = createMember();
			result.getPrincipals().add(member.getPrincipal());
			result.getMessageBoxes().addAll(member.getMessageBoxes());
			result.getMembers().add(member);
		}

		int recipientCount = 2;
		for (int i = 0; i < 3; i++) {
			Member sender = result.getMembers().get(i % memberCount);
			List<Member> recipients = new ArrayList<Member>();
			for (int j = 0; j < recipientCount; j++) {
				Member recipient = result.getMembers().get(
						(i + j + 1) % memberCount);
				recipients.add(recipient);
			}
			result.getMessages().addAll(createMessages(sender, recipients));
		}
		
		

		return result;
	}

	private static List<Message> createMessages(Member sender,
			List<Member> recipients) {
		List<Message> result = new ArrayList<Message>();
		String title = "sample title";
		String content = "sample content";

		result.add(createMessage(sender, recipients, title, content,
				sender.getSentItems()));

		for (Member recipient : recipients) {
			result.add(createMessage(sender, recipients, title, content,
					recipient.getInbox()));
		}

		return result;
	}

	private static Message createMessage(Member sender,
			List<Member> recipients, String title, String content,
			MessageBox location) {

		Message message = new Message();
		message.setId(getUuid());
		message.setSender(sender);
		message.setRecipients(recipients);
		message.setTitle(title);
		message.setContent(content);
		message.setStatus(Status.NEW);
		message.setLocation(location);

		return message;
	}

}
