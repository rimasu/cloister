package org.rimasu.cloister.server.backup;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.rimasu.cloister.server.model.auth.Principal;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.Message;
import org.rimasu.cloister.server.model.core.MessageBox;
import org.rimasu.cloister.server.model.event.Callback;

/**
 * Snapshot contains the entire state of the model. This is used as the root
 * element when generating or parsing XML.
 */
@XmlType(name = "", propOrder = { "principals", "messageBoxes", "members", "messages", "callbacks" })
@XmlRootElement(name = "BackUp")
public class Snapshot {
	
		
	/**
	 * All the principals.
	 */
	private List<Principal> principals;

	
	/**
	 * All the message boxes.
	 */
	private List<MessageBox> messageBoxes;

	/**
	 * All the members.
	 */
	private List<Member> members;

	/**
	 * All the mesages.
	 */
	private List<Message> messages;
	
	/**
	 * All the callbacks.
	 */
	private List<Callback> callbacks;

	/**
	 * Constructor. Create a new, empty, snapshot. All the collection are
	 * initialised so can be accessed directly via getters (no need to use
	 * setters).
	 */
	public Snapshot() {
		messageBoxes = new ArrayList<MessageBox>();
		members = new ArrayList<Member>();		
		messages = new ArrayList<Message>();
		principals = new ArrayList<Principal>();
		callbacks = new ArrayList<Callback>();
	}

	@Valid
	@XmlElementWrapper(name = "members")
	@XmlElement(name = "Member")
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Valid
	@XmlElementWrapper(name = "messageBoxes")
	@XmlElement(name = "MessageBox")
	public List<MessageBox> getMessageBoxes() {
		return messageBoxes;
	}

	public void setMessageBoxes(List<MessageBox> messageBoxes) {
		this.messageBoxes = messageBoxes;
	}

	@Valid
	@XmlElementWrapper(name = "messages")
	@XmlElement(name = "Message")
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Valid
	@XmlElementWrapper(name = "principals")
	@XmlElement(name = "Principal")
	public List<Principal> getPrincipals() {
		return principals;
	}

	public void setPrincipals(List<Principal> principals) {
		this.principals = principals;
	}

	@Valid
	@XmlElementWrapper(name = "callbacks")
	@XmlElement(name = "Callback")
	public List<Callback> getCallbacks() {
		return callbacks;
	}

	public void setCallbacks(List<Callback> callbacks) {
		this.callbacks = callbacks;
	}

}
