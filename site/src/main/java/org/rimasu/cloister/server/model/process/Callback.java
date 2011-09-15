package org.rimasu.cloister.server.model.process;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlIDREF;

import org.rimasu.cloister.server.model.AbstractEntity;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.Message;


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

	@NotNull
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(nullable=false)
	@XmlIDREF
	public Member getSubject() {
		return subject;
	}

	public void setSubject(Member subject) {
		this.subject = subject;
	}

	@NotNull	
	@Column(nullable=false)
	@Temporal(value = TemporalType.TIMESTAMP)
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

	public boolean hasExpired() {
		return expiryDate.before(Calendar.getInstance());
	}
	
	

}
