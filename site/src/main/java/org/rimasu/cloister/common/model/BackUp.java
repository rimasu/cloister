package org.rimasu.cloister.common.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BackUp")
public class BackUp {	
	
	private List<Member> members;
	
	private List<MessageBox> messageBoxes;
	
	private List<Message> messages;
	
	
	
	@XmlElementWrapper(name="members")
    @XmlElement(name="Member")
	public List<Member> getMembers() {
		return members;
	}
	
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	@XmlElementWrapper(name="messageBoxes")
    @XmlElement(name="MessageBox")
	public List<MessageBox> getMessageBoxes() {
		return messageBoxes;
	}

	public void setMessageBoxes(List<MessageBox> messageBoxes) {
		this.messageBoxes = messageBoxes;
	}
	
	
	@XmlElementWrapper(name="messages")
    @XmlElement(name="Message")
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	
	
}
