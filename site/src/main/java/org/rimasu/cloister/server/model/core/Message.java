package org.rimasu.cloister.server.model.core;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;

import org.rimasu.cloister.server.model.AbstractEntity;


@Entity
@Table(name="MESSAGES")
public class Message extends AbstractEntity  {
	@XmlEnum
	public enum Status
	{
		NEW,
		READ,
		DELETED
	}
	
	private MessageBox location;
	
	private Member sender;
	
	private List<Member> recipients;
	
	private Calendar sendDate;
	
	private String title;
	
	private Status status;
	

	@XmlIDREF
	@ManyToOne
	public MessageBox getLocation() {
		return location;
	}

	public void setLocation(MessageBox location) {
		this.location = location;
	}
	
	@XmlIDREF
	public Member getSender() {
		return sender;
	}

	public void setSender(Member sender) {
		this.sender = sender;
	}

	@XmlElementWrapper(name="recipients")
    @XmlElement(name="recipient")
	@XmlIDREF
	@JoinTable(name = "MESSAGE_RECIPIENTS", joinColumns = @JoinColumn(name = "MESSAGE"))
	//@JoinColumn(name="RECIPIENT")
	public List<Member> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Member> recipients) {
		this.recipients = recipients;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@XmlSchemaType(name="date")
	public Calendar getSendDate() {
		return sendDate;
	}

	public void setSendDate(Calendar sendDate) {
		this.sendDate = sendDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String content;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Message> findAll(EntityManager manager) {
		Query query = manager.createQuery("SELECT e FROM Message e");
		return (List<Message>) query.getResultList();
	}

}
