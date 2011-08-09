package org.rimasu.cloister.common.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Member is responsible for storing the all details stored for a member, apart
 * from security credentials (username/password/roles).
 * 
 * Each member as a randomly generated UUID which is unique with respect to
 * other members (and likely every other UUID). The UUID should never be exposed
 * via a user interface.
 */
@XmlType(name="", propOrder={"firstName", "interests"})
@Entity
public class Member {

	private String uuid;

	private String firstName;

	private List<Interest> interests;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Id
	@XmlID
	@XmlAttribute
	@NotNull(message = "{uuid.null}")
	@Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", message = "{uuid.valid}")
	public String getUuid() {
		return uuid;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	@NotNull(message = "{member.firstName.null}")
	@Size(min = 2, message = "{member.firstName.length}")
	@Pattern(regexp = "[A-Za-z\\.\\-\\']*", message = "{member.firstName.legalChars}")
	public String getFirstName() {
		return firstName;
	}

	
	@XmlElementWrapper(name="interests")
    @XmlElement(name="Interest")
    @ElementCollection
	@CollectionTable(
	        name="MEMBER_INTERESTS",
	        joinColumns=@JoinColumn(name="MEMBER_ID")
	)		    
	@OrderColumn(name="sortOrder")
	public List<Interest> getInterests() {
		if (interests == null) {
			interests = new ArrayList<Interest>();
		}
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
}
