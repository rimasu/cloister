package org.rimasu.cloister.server.model.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;

import org.rimasu.cloister.server.model.AbstractEntity;

@Entity
@Table(name="MESSAGEBOXES")
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
	
	@SuppressWarnings("unchecked")
	public static List<MessageBox> findAll(EntityManager manager) {
		Query query = manager.createQuery("SELECT e FROM MessageBox e");
		return (List<MessageBox>) query.getResultList();
	}
}
