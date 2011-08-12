package org.rimasu.cloister.server.model.auth;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Role {
	
	ADMIN,
	
	SUPERVISOR,
	
	MEMBER,
	
	APPLICANT
}
