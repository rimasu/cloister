package org.rimasu.cloister.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.rimasu.cloister.common.model.ValidationReport.Issue;
import org.rimasu.cloister.common.model.ValidationReport.Target;

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

	/**
	 * Pattern used to validate firstName field.
	 */
	private static final Pattern FIRST_NAME_PATTERN = Pattern
			.compile("[A-Za-z\\.\\-\\']{2,}?");

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

	@NotNull
	@Size(min=2)
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Create a validation report detailing issues with member configuration.
	 * 
	 * @return validation report.
	 */
	public List<ValidationReport> validate() {
		List<ValidationReport> reports = new ArrayList<ValidationReport>();

		if (uuid == null) {
			reports.add(new ValidationReport(Target.MEMBER, uuid, "UUID",
					Issue.NOT_POPULATED));
		} else {
			try {
				UUID.fromString(uuid);
			} catch (IllegalArgumentException e) {
				reports.add(new ValidationReport(Target.MEMBER, uuid, "UUID",
						Issue.NOT_VALID));
			}
		}

		if (firstName == null) {
			reports.add(new ValidationReport(Target.MEMBER, uuid, "First Name",
					Issue.NOT_POPULATED));
		} else if (!FIRST_NAME_PATTERN.matcher(firstName).matches()) {
			reports.add(new ValidationReport(Target.MEMBER, uuid, "First Name",
					Issue.NOT_VALID));
		}

		return reports;
	}
}
