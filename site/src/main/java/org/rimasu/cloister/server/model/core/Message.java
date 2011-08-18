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
import javax.validation.constraints.NotNull;
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

	private String content;
	
	private Status status;
	

	@XmlIDREF
	@ManyToOne
	@NotNull
	@JoinColumn(nullable=false)
	public MessageBox getLocation() {
		return location;
	}

	public void setLocation(MessageBox location) {
		this.location = location;
	}
	
	@XmlIDREF
	@NotNull
	@JoinColumn(nullable=false)
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
	public List<Member> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Member> recipients) {
		this.recipients = recipients;
	}

	@NotNull
	@Column(nullable=false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@XmlSchemaType(name="date")
	public Calendar getSendDate() {
		return sendDate;
	}

	public void setSendDate(Calendar sendDate) {
		this.sendDate = sendDate;
	}

	@NotNull
	@Column(nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	@Column(nullable=false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@NotNull
	@Column(nullable=false)
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
