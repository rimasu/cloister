package org.rimasu.cloister.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
	@Pattern(regexp="[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}")
	public String getUuid() {
		return uuid;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	@NotNull
	@Size(min=2)
	public String getFirstName() {
		return firstName;
	}


}
