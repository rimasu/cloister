package org.rimasu.cloister.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Member is responsible for storing the all details stored for a member, apart
 * from security credentials (username/password/roles).
 * 
 * Each member as a randomly generated UUID which is unique with respect to
 * other members (and likely every other UUID). The UUID should never be exposed
 * via a user interface.
 */
@XmlRootElement
@Entity
public class Member {

	private String uuid;	

	private String firstName;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Id
	public String getUuid() {
		return uuid;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getFirstName() {
		return firstName;
	}
}
