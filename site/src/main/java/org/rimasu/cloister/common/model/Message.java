package org.rimasu.cloister.common.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;


@Entity
public class Message  {
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
	
	private String uuid;

	@Id
	@XmlID
	@XmlAttribute
	@NotNull(message = "{uuid.null}")
	@Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", message = "{uuid.valid}")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

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

}
