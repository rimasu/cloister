package org.rimasu.cloister.server.model.event;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlEnum;

import org.rimasu.cloister.server.model.AbstractEntity;
import org.rimasu.cloister.server.model.core.Member;


@Entity
public class Callback extends AbstractEntity {

	@XmlEnum
	public enum Type
	{
		EMAIL_CONFIRMATION,
		PASSWORD_RESET
	}
	
	private Type type;
	
	private Member subject;
	
	private Calendar expiryDate;
	
	private boolean used;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Member getSubject() {
		return subject;
	}

	public void setSubject(Member subject) {
		this.subject = subject;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isExpired() {
		return false;
	}
}
