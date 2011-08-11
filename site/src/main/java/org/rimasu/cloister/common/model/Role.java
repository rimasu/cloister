package org.rimasu.cloister.common.model;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Role {
	ADMIN,
	SUPERVISOR,
	MEMBER,
	APPLICANT
}
