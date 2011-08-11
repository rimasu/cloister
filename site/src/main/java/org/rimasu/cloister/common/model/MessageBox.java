package org.rimasu.cloister.common.model;

import javax.persistence.Entity;

@Entity
public class MessageBox extends AbstractEntity {

	private String name;

	public MessageBox() {
	}

	public MessageBox(String uuid, String name) {
		super(uuid);
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
